
package DTO;



public class RenameMeDTO {

    private String id;
    private String index;
    private String name;
    private String description;

    public RenameMeDTO(String id, String index, String name, String description)
    {
        this.id = id;
        this.index = index;
        this.name = name;
        this.description = description;
    }
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIndex()
    {
        return index;
    }

    public void setIndex(String index)
    {
        this.index = index;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
