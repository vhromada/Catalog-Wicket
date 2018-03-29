package cz.vhromada.catalog.web.picture.mo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * A class represents MO for picture.
 *
 * @author Vladimir Hromada
 */
public class PictureMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * Picture
     */
    private byte[] content;

    /**
     * Position
     */
    private int position;

    /**
     * Returns ID.
     *
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets a new value to ID.
     *
     * @param id new value
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Returns content.
     *
     * @return content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Sets a new value to content.
     *
     * @param content new value
     */
    public void setContent(final byte[] content) {
        this.content = content;
    }

    /**
     * Returns position.
     *
     * @return position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets a new value to position.
     *
     * @param position new value
     */
    public void setPosition(final int position) {
        this.position = position;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PictureMO) || id == null) {
            return false;
        }

        return id.equals(((PictureMO) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("PictureMO [id=%d, content=%s, position=%d]", id, Arrays.toString(content), position);
    }

}
