package vcpe.st10118615.mooiste.model;

public class CartItem {
    private Integer Id;
    private Integer CartId;
    private Integer ProductId;
    private Integer Qty;

    public CartItem() {
    }

    public CartItem(Integer id, Integer cartId, Integer productId, Integer qty) {
        Id = id;
        CartId = cartId;
        ProductId = productId;
        Qty = qty;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCartId() {
        return CartId;
    }

    public void setCartId(Integer cartId) {
        CartId = cartId;
    }

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }
}
