package cz.vhromada.catalog.web.picture.panel;

import java.util.List;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.picture.mo.PictureMO;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Bytes;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * A class represents panel with form for picture.
 *
 * @author Vladimir Hromada
 */
@Component(PictureFormPanel.ID)
@Scope("prototype")
public class PictureFormPanel extends AbstractFormPanel<PictureMO> {

    /**
     * ID
     */
    public static final String ID = "pictureFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field for file upload
     */
    private FileUploadField fileUpload;

    /**
     * Creates a new instance of PictureFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public PictureFormPanel(final String id, final Model<PictureMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        fileUpload = new FileUploadField("file");

        getForm().setMultiPart(true);
        getForm().setFileMaxSize(Bytes.megabytes(2));
        getForm().setMaxSize(Bytes.megabytes(2));
        getForm().add(fileUpload);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.PICTURES_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<PictureMO> panelForm) {
        final List<FileUpload> files = fileUpload.getFileUploads();
        if (!CollectionUtils.isEmpty(files)) {
            panelForm.getModelObject().setContent(files.get(0).getBytes());
        }
    }

}
