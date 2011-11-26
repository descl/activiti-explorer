/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.explorer.ui.form;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.form.FormProperty;
import org.activiti.explorer.ExplorerApp;
import org.activiti.explorer.I18nManager;
import org.activiti.explorer.ui.mainlayout.ExplorerLayout;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.VerticalLayout;

/**
 * Form that renders form-properties and allows posting the filled in value. Performs
 * validation as well. Exposes {@link FormPropertiesEvent}s which allow listening for 
 * submission and cancellation of the form.
 * 
 * @author Frederik Heremans
 */
public class FormPropertiesForm extends VerticalLayout {

  private static final long serialVersionUID = -3197331726904715949L;

	// Services
	protected FormService formService;
	protected I18nManager i18nManager;

	// UI
	protected Label formTitle;
	protected List<ButtonWithValue> submitFormButtons;
	protected FormPropertiesComponent formPropertiesComponent;
	private HorizontalLayout buttonHolder;
	private FormProperty submitProperty;
	private Button submitButton;
	private Button cancelButton;

	public FormPropertiesForm() {
		super();
		formService = ProcessEngines.getDefaultProcessEngine().getFormService();
		i18nManager = ExplorerApp.get().getI18nManager();

		addStyleName(ExplorerLayout.STYLE_DETAIL_BLOCK);
		addStyleName(ExplorerLayout.STYLE_FORM_PROPERTIES);

		initTitle();
		initFormPropertiesComponent();
		initButtons();
		initListeners();
	}

	public void setFormHelp(String caption) {
		formTitle.setValue(caption);
		formTitle.setVisible(caption != null);
	}

	/**
	 * Clear all (writable) values in the form.
	 */
	public void clear() {
		formPropertiesComponent.setFormProperties(formPropertiesComponent
				.getFormProperties());
	}
	
	public void setSubmitButtonCaption(String cap){
		if(submitButton!=null)
			submitButton.setCaption(cap);
	}
	
	public void setCancelButtonCaption(String cap){
		if(cancelButton!=null)
			cancelButton.setCaption(cap);
	}

	protected void initTitle() {
		formTitle = new Label();
		formTitle.addStyleName(ExplorerLayout.STYLE_H4);
		formTitle.setVisible(false);
		addComponent(formTitle);
	}

	protected void initButtons() {
		submitFormButtons = new LinkedList<ButtonWithValue>();
		buttonHolder = new HorizontalLayout();
		buttonHolder.setSpacing(true);
		buttonHolder.setWidth(100, UNITS_PERCENTAGE);
		buttonHolder.addStyleName(ExplorerLayout.STYLE_DETAIL_BLOCK);
		addButtons();
		addComponent(buttonHolder);
	}

	protected Button addButton(String id, String name) {
		Button b = new Button();
		b.setCaption(name);
		buttonHolder.addComponent(b);
		buttonHolder.setComponentAlignment(b, Alignment.BOTTOM_RIGHT);
		submitFormButtons.add(new ButtonWithValue(b, id));
		return b;
	}

	protected void addButtons() {
		if (submitProperty == null) {
			submitButton = addButton(null, "Complete");
			cancelButton = addButton(null, "Cancel");
		} else {
			// I believe this is a map from ids to names
			Map<String, String> formValues = (Map<String, String>) submitProperty
					.getType().getInformation("values");
			for (Entry<String, String> formValue : formValues.entrySet()) {
				addButton(formValue.getKey(), formValue.getValue());
			}
		}

		Label buttonSpacer = new Label();
		buttonHolder.addComponent(buttonSpacer);
		buttonHolder.setExpandRatio(buttonSpacer, 1.0f);

	}

	protected void initFormPropertiesComponent() {
		formPropertiesComponent = new FormPropertiesComponent();
		addComponent(formPropertiesComponent);
	}

	private class ClickListenerWithValue implements ClickListener {
		private final String value;
		private final Button button;

		public ClickListenerWithValue(ButtonWithValue bwv) {
			super();
			this.value = bwv.value;
			this.button = bwv.button;
		}

		public void buttonClick(ClickEvent event) {
			// Extract the submitted values from the form. Throws
			// exception when validation fails.
			try {
				Map<String, String> formProperties = formPropertiesComponent
						.getFormPropertyValues();
				if (value != null) {
					formProperties.put(submitProperty.getId(), value);
				}
				fireEvent(new FormPropertiesEvent(
						FormPropertiesForm.this,
						FormPropertiesEvent.TYPE_SUBMIT, formProperties));
				this.button.setComponentError(null);
			} catch (InvalidValueException ive) {
				// Error is presented to user by the form component
			}
		}
	}

	protected void initListeners() {
		for (ButtonWithValue bwv : submitFormButtons) {
			bwv.button.addListener(new ClickListenerWithValue(bwv));
		}
	}

	protected void addEmptySpace(ComponentContainer container) {
		Label emptySpace = new Label("&nbsp;", Label.CONTENT_XHTML);
		emptySpace.setSizeUndefined();
		container.addComponent(emptySpace);
	}

	private class ButtonWithValue {
		public final Button button;
		public final String value;

		public ButtonWithValue(Button b, String v) {
			button = b;
			value = v;
		}
	}

	/**
	 * Event indicating a form has been submitted or cancelled. When submitted,
	 * the values of the form-properties are available.
	 * 
	 * @author Frederik Heremans
	 */
	public class FormPropertiesEvent extends Event {

		private static final long serialVersionUID = -410814526942034125L;

		public static final String TYPE_SUBMIT = "SUBMIT";
		public static final String TYPE_CANCEL = "CANCEL";

		private String type;
		private Map<String, String> formProperties;

		public FormPropertiesEvent(Component source, String type) {
			super(source);
			this.type = type;
		}

		public FormPropertiesEvent(Component source, String type,
				Map<String, String> formProperties) {
			this(source, type);
			this.formProperties = formProperties;
		}

		public String getType() {
			return type;
		}

		public Map<String, String> getFormProperties() {
			return formProperties;
		}
	}

	public void setFormProperties(List<FormProperty> formProperties) {
		submitProperty = null;
		List<FormProperty> newList = new LinkedList<FormProperty>();
		for (FormProperty fp : formProperties) {
			if (fp.getName().equals("__SUBMIT__")
					&& fp.getType().getName().equals("enum"))
				submitProperty = fp;
			else
				newList.add(fp);
		}
		if (submitProperty != null)
			resetButtons(submitProperty);
		formPropertiesComponent.setFormProperties(newList);
	}

	protected void resetButtons(FormProperty submitProperty) {
		buttonHolder.removeAllComponents();
		addButtons();
		initListeners();
	}
}
