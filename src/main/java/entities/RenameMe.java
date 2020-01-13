package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "RenameMe")
public class RenameMe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RenameMe", length = 20)
    private String renameMe;
    
    @ManyToMany(mappedBy = "RenameMe")  // ManyToMany & ManyToOne & OneToMany & OneToOne
    private List<User> userList;

    public RenameMe() {
    }

    public RenameMe(String renameMe) {
        this.renameMe = renameMe;
    }

    public String getRoleName() {
        return renameMe;
    }

    public void setRoleName(String renameMe) {
        this.renameMe = renameMe;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    
}
