package map;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public Direction getOpposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }

    public String getTrad() {
        return switch (this) {
            case NORTH -> "Nord";
            case SOUTH -> "Sud";
            case EAST -> "Est";
            case WEST -> "Ouest";
        };
    }

    public static Direction parse(String input) {
        return switch (input.trim().toUpperCase()) {
            case "N", "NORD", "NORTH" -> NORTH;
            case "S", "SUD", "SOUTH" -> SOUTH;
            case "E", "EST", "EAST" -> EAST;
            case "O", "W", "OUEST", "WEST" -> WEST;
            default -> null;
        };
    }
}