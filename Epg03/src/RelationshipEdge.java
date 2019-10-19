import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.Attribute;

public class RelationshipEdge extends DefaultEdge
{
    private static final long serialVersionUID = 8238755873387699328L;

    private Object v1;
    private Object v2;
    private Map<String, Attribute> att;

    public RelationshipEdge(Object v1, Object v2, Map<String, Attribute> att)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.att = att;
    }

    public Object getLabel()
    {
        Object o = att.get("label");
        if (o == null)
        {
            return ("{" + v1 + "," + v2 + "}");
        }
        else
            return o;
    }

    public Object getV1()
    {
        return v1;
    }

    public Object getV2()
    {
        return v2;
    }

    public Object getNeighbour(Object v)
    {
        if (v.equals(v1))
        {
            return v2;
        }
        else
            return v1;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
        result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
        return result;
    }

    public boolean equals(RelationshipEdge e)
    {
        return (this.getLabel()).equals(e.getLabel());
    }

    @Override
    public String toString()
    {
        Object o = att.get("label");
        if (o == null)
        {
            return ("{" + v1 + "," + v2 + "}");
        }
        else
            return (att.get("label")).toString() + "->{" + v1 + ";" + v2 + "}";
    }
}