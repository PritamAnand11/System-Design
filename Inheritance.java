class Car {
    protected String brand;
    protected String model;
    protected boolean isEngineOn;
    protected int currentSpeed;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isEngineOn = false;
        this.currentSpeed = 0;
    }
    public void startEngine() {
        isEngineOn = true;
        System.out.println(brand + " " + model + " : Engine started.");
    }

    public void stopEngine() {
        isEngineOn = false;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

    public void accelerate() {
        if (!isEngineOn) {
            System.out.println(brand + " " + model + " : Cannot accelerate! Engine is off.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }

    public void brake() {
        currentSpeed -= 20;
        if (currentSpeed < 0) currentSpeed = 0;
        System.out.println(brand + " " + model + " : Braking! Speed is now " + currentSpeed + " km/h");
    }
}

class ManualCar extends Car {  // Inherits from Car
    
    private int currentGear; //spcific to Manual Car.

    public ManualCar(String brand, String model) {
        super(brand, model);
        this.currentGear = 0;
    }

    // Specialized method for Manual Car
    public void shiftGear(int gear) {
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }
}


class ElectricCar extends Car {  // Inherits from Car
    
    public int batteryLevel; //spcific to Electric Car.

    public ElectricCar(String brand, String model) {
        super(brand, model);
        this.batteryLevel = 100;
    }

    // Specialized method for Electric Car
    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println(brand + " " + model + " : Battery fully charged!");
    }
}

// Main Class
public class Inheritance {
    public static void main(String[] args) {
        ManualCar myManualCar = new ManualCar("Suzuki", "WagonR");
        myManualCar.startEngine();
        myManualCar.shiftGear(1); // Specific to Manual Car
        myManualCar.accelerate();
        myManualCar.brake();
        myManualCar.stopEngine();

        System.out.println("----------------------");

        ElectricCar myElectricCar = new ElectricCar("Tesla", "Model S");
        myElectricCar.chargeBattery(); // Specific to Electric Car
        myElectricCar.startEngine();
        myElectricCar.accelerate();
        myElectricCar.brake();
        myElectricCar.stopEngine();
    }
}