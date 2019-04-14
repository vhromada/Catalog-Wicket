package cz.vhromada.catalog.web.component;

import java.io.IOException;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.validation.result.Result;
import cz.vhromada.validation.result.Status;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.time.Duration;

/**
 * A class represents resource for image.
 *
 * @author Vladimir Hromada
 */
public class ImageResource extends AbstractResource {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Facade for pictures
     */
    @SpringBean
    @SuppressWarnings("unused")
    private PictureFacade pictureFacade;

    /**
     * ID
     */
    private Integer id;

    /**
     * Creates a new instance of ImageResource.
     *
     * @param id ID
     */
    public ImageResource(final Integer id) {
        this.id = id;

        Injector.get().inject(this);
    }

    @Override
    protected ResourceResponse newResourceResponse(final IResource.Attributes attributes) {
        final Result<Picture> result = pictureFacade.get(id);
        if (Status.OK != result.getStatus()) {
            throw new IllegalArgumentException("Error in getting picture. " + result.getEvents());
        }
        final Picture picture = result.getData();

        final ResourceResponse response = new ResourceResponse();
        response.setCacheDuration(Duration.NONE);
        response.setContentType("image/jpg");
        response.setFileName("picture.jpg");
        response.setWriteCallback(new AbstractResource.WriteCallback() {
            @Override
            public void writeData(final IResource.Attributes callbackAttributes) throws IOException {
                callbackAttributes.getResponse().getOutputStream().write(picture.getContent());
            }
        });

        return response;
    }
}
