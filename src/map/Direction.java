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
            case NORTH -> "Haut";
            case SOUTH -> "Bas";
            case EAST -> "Droite";
            case WEST -> "Gauche";
        };
    }
}
