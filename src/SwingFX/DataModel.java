/*
 * 
 */
package SwingFX;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class DataModel.
 */
public class DataModel {
    
    /** The label value property. */
    private StringProperty labelValueProperty;

    /**
     * Label value property.
     *
     * @return the string property
     */
    public StringProperty labelValueProperty() {
        if(labelValueProperty == null){
            labelValueProperty = new SimpleStringProperty();
        }
        return labelValueProperty;
    }

    /**
     * Sets the label value.
     *
     * @param labelValue the new label value
     */
    public void setLabelValue(String labelValue) {
        labelValueProperty().set(labelValue);
    }

    /**
     * Gets the label value.
     *
     * @return the label value
     */
    public String getLabelValue() {
        return labelValueProperty().get();
    }
    
    
}