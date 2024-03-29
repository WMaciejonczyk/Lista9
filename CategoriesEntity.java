package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "categories", schema = "mac_menu")
public class CategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cat_id")
    private String catId;
    @Basic
    @Column(name = "cat_name")
    private String catName;
    @OneToMany(mappedBy = "category")
    private Collection<ProductsEntity> productsByCatId;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesEntity that = (CategoriesEntity) o;

        if (catId != null ? !catId.equals(that.catId) : that.catId != null) return false;
        if (catName != null ? !catName.equals(that.catName) : that.catName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = catId != null ? catId.hashCode() : 0;
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        return result;
    }

    public Collection<ProductsEntity> getProductsByCatId() {
        return productsByCatId;
    }

    public void setProductsByCatId(Collection<ProductsEntity> productsByCatId) {
        this.productsByCatId = productsByCatId;
    }
}
