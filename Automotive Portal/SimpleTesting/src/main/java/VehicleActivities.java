public interface VehicleActivities {
    boolean isMovingFast (int speed);
    String makeNoise();
    default void greeting(){
        System.out.println("Hello! I'm Vehicle! "+makeNoise());
    }
}
