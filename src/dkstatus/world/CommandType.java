
package dkstatus.world;

/**
 *
 * @author Johny
 */
public enum CommandType {
    INCOMING_ATTACK("Útok", true, "attack.png"),
    INCOMING_SUPPORT("Podpora", true, "support.png"),
    UNKNOWN_I("", true, "return.png"),
    
    OUTGOING_ATTACK("Útok", false, "attack.png"),
    OUTGOING_SUPPORT("Podpora", false, "support.png"),
    RETRIEVING_SUPPORT("Ústup", false, "back.png"),
    SENT_BACK_SUPPORT("Zaslán", false, "back.png"),
    RETURNING_ATTACK("Návrat", false, "return.png"),
    CANCELLED("Přerušený", false, "cancel.png"),
    UNKNOWN_O("", false, "return.png");
    
    private final String parseString;
    private final boolean incoming;
    private final String icon;

    private CommandType(String parseString, boolean incoming, String icon) {
        this.parseString = parseString;
        this.incoming = incoming;
        this.icon = icon;
    }

    public String getParseString() {
        return parseString;
    }

    public boolean isIncoming() {
        return incoming;
    }

    public String getIcon() {
        return icon;
    }
}
