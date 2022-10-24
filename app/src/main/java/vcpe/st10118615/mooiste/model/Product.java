package vcpe.st10118615.mooiste.model;

public class Product {
    private Integer Id;
    private String Name;
    private String Description;
    private String ImageURL;
    private double Price;
    private int Qty;
    private int CategoryId;

    public Product() {
    }

    public Product(Integer id,
                   String name,
                   String description,
                   String imageURL, double price, int qty, int categoryId) {
        Id = id;
        Name = name;
        Description = description;
        ImageURL = imageURL;
        Price = price;
        Qty = qty;
        CategoryId = categoryId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }
}
