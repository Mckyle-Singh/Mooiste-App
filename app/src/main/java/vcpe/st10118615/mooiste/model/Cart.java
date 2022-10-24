package vcpe.st10118615.mooiste.model;

public class Cart {
    private Integer Id;
    private Integer UserId;

    public Cart() {
    }

    public Cart(Integer id, Integer userId) {
        Id = id;
        UserId = userId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
