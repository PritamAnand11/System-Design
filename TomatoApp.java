import java.util.*;

class Restaurant {
    private String name;
    private String location;
    private List<MenuItem> menu = new ArrayList<>();

    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
}

class MenuItem {
    private String code;
    private String name;
    private double price;

    public MenuItem(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class Cart {
    private Restaurant restaurant;
    private List<MenuItem> items = new ArrayList<>();

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }
}

class User {
    private String name;
    private Cart cart = new Cart();

    public User(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    public String getName() {
        return name;
    }
}

class Order {
    private User user;
    private List<MenuItem> items;
    private double totalCost;

    public Order(User user, List<MenuItem> items, double totalCost) {
        this.user = user;
        this.items = items;
        this.totalCost = totalCost;
    }

    public void printOrder() {
        System.out.println("Order for " + user.getName());
        for (MenuItem item : items) {
            System.out.println(item.getName() + " - Rs." + item.getPrice());
        }
        System.out.println("Total: Rs." + totalCost);
    }
}

// main app
public class TomatoApp {
    private List<Restaurant> restaurants = new ArrayList<>();

    public TomatoApp() {
        // Sample data
        Restaurant r1 = new Restaurant("Bikaner", "Delhi");
        r1.addMenuItem(new MenuItem("P1", "Chole Bhature", 120));
        r1.addMenuItem(new MenuItem("P2", "Samosa", 15));

        Restaurant r2 = new Restaurant("Haldiram", "Kolkata");
        r2.addMenuItem(new MenuItem("P1", "Raj Kachori", 80));
        r2.addMenuItem(new MenuItem("P2", "Pav Bhaji", 100));

        restaurants.add(r1);
        restaurants.add(r2);
    }

    public List<Restaurant> searchRestaurants(String location) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getLocation().equalsIgnoreCase(location)) {
                result.add(r);
            }
        }
        return result;
    }

    public void selectRestaurant(User user, Restaurant restaurant) {
        user.getCart().setRestaurant(restaurant);
    }

    public void addToCart(User user, String itemCode) {
        Restaurant r = user.getCart().getRestaurant();
        if (r == null) {
            System.out.println("No restaurant selected!");
            return;
        }
        for (MenuItem item : r.getMenu()) {
            if (item.getCode().equalsIgnoreCase(itemCode)) {
                user.getCart().addItem(item);
                System.out.println(item.getName() + " added to cart.");
                return;
            }
        }
        System.out.println("Item code not found.");
    }

    public Order checkout(User user) {
        List<MenuItem> items = user.getCart().getItems();
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return new Order(user, items, total);
    }

    public static void main(String[] args) {
        TomatoApp app = new TomatoApp();
        User user = new User("Pritam Anand");

        // Search restaurants in Delhi
        List<Restaurant> delhiRestaurants = app.searchRestaurants("Delhi");
        if (!delhiRestaurants.isEmpty()) {
            Restaurant chosen = delhiRestaurants.get(0);
            app.selectRestaurant(user, chosen);

            // Add items to cart
            app.addToCart(user, "P1");
            app.addToCart(user, "P2");

            // Checkout
            Order order = app.checkout(user);
            order.printOrder();
        } else {
            System.out.println("No restaurants found in Delhi.");
        }
    }
}
