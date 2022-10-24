package vcpe.st10118615.mooiste.model;

public class ProductCategory {
    private Integer Id;
    private String Name;

    public ProductCategory() {
    }

    public ProductCategory(Integer id, String name) {
        Id = id;
        Name = name;
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
}

