package xyz.yooniks.natalciaguilds.bukkit.element.tablist;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

public class ProtocolLibTablist implements Tablist {

  private final Map<Integer, String> elements;
  private final String header, footer;

  public ProtocolLibTablist(String header, String footer, Map<Integer, String> elements) {
    this.header = header;
    this.footer = footer;
    this.elements = elements;
  }

  @Override
  public void update(Player player) {
    if (player == null || !player.isOnline()) {
      return;
    }
    final PacketContainer footerAndHeader = new PacketContainer(
        PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
    footerAndHeader.getChatComponents().write(0, WrappedChatComponent.fromText(this.header));
    footerAndHeader.getChatComponents().write(1, WrappedChatComponent.fromText(this.footer));

    final PacketContainer info = new PacketContainer(PacketType.Play.Server.PLAYER_INFO);
    info.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.ADD_PLAYER);

    final List<PlayerInfoData> infoData = new ArrayList<>();
    for (Map.Entry<Integer, String> entry : this.elements.entrySet()) {
      infoData.add(new PlayerInfoData(new WrappedGameProfile(UUID.randomUUID(), ""),
          0, EnumWrappers.NativeGameMode.NOT_SET,
          WrappedChatComponent.fromText(entry.getValue())));
    }

    info.getPlayerInfoDataLists().write(0, infoData);

    try {
      ProtocolLibrary.getProtocolManager().sendServerPacket(player, footerAndHeader);
      ProtocolLibrary.getProtocolManager().sendServerPacket(player, info);
    } catch (InvocationTargetException ex) {
      ex.printStackTrace();
    }
  }

}
