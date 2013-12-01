/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bullshit_paper_gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.collections.*;
import bullshit_paper.*;
import java.io.*;
import java.util.*;
import javafx.beans.value.*;
import javafx.stage.FileChooser;
import javafx.concurrent.*;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Michal
 */
public class MainWindowController implements Initializable
{
    @FXML
    private TextField _titleTF;
    @FXML
    private CheckBox _sectionSudokuCB;
    @FXML
    private TextField _sectionNameTF;
    @FXML
    private TextField _sectionTagsTF;
    @FXML
    private ColorPicker _sectionHeaderColorPicker;
    @FXML
    private ListView<String> _sectionsLV;
    @FXML
    private Button _renderBt;
    @FXML
    private Button _removeSectionBt;
    @FXML
    private Button _addSectionBt;
    @FXML
    private Label _statusLabel;
    @FXML
    private GridPane _mainPane;
    
    private Control[] _sectionControls;
    private boolean _removing = false;
    private int _ix = -1;
    private final List<SectionInfo> _sections = new ArrayList<>();
    private final ObservableList<String> _sectionsNames = FXCollections.observableArrayList();
    private final SectionInfo _newSection = new SectionInfo(new ArrayList<String>(), "[New section]", java.awt.Color.RED, false);

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
	_sectionsLV.setItems(_sectionsNames);
	_sectionsLV.getSelectionModel().selectedIndexProperty().addListener(new SectionUpdater());
	_sectionControls = new Control[] { _removeSectionBt, _sectionNameTF, _sectionTagsTF, _sectionHeaderColorPicker, _sectionSudokuCB };
	_sectionNameTF.textProperty().addListener(new ChangeListener<String>() {
	    @Override public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
		_sectionsNames.set(_ix, newValue);
	    }});
	for (Control c : _sectionControls) c.setDisable(true);
    }

    @FXML
    private void handleRender(ActionEvent event)
    {
	if (_sections.isEmpty()) { setStatus("You have to define at least one section."); return; }
	setStatus("");
	if (_ix != -1) _sections.set(_ix, saveSection());
	FileChooser chooser = new FileChooser();
	chooser.setInitialFileName(_titleTF.getText());
	chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
	File file = chooser.showSaveDialog(null);
	if (file == null) return;
	if (!file.getName().toLowerCase().endsWith(".pdf")) file = new File(file.getParent(), file.getName() + ".pdf");	    
	disableUI(true);
	setStatus("Generating...");
	GeneratePaperTask task = new GeneratePaperTask(file, _titleTF.getText(), _sections);
	task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	    @Override public void handle(WorkerStateEvent event) { setStatus("OK"); disableUI(false); } });
	task.setOnFailed(new EventHandler<WorkerStateEvent>() {
	    @Override public void handle(WorkerStateEvent event) {
		Throwable ex = event.getSource().getException();
		if (ex == null) setStatus("Fail");
		else setStatus(ex.toString());
		disableUI(false);
	    }});
	 Thread t = new Thread(task);
	 t.setDaemon(true);
	 t.start();
    }
    
    private void disableUI(boolean disable)
    {
	_mainPane.setDisable(disable);
    }
    
    private static class GeneratePaperTask extends Task<Void>
    {
	private final File _file;
	private final String _title;
	private final List <SectionInfo> _sections;
	
	public GeneratePaperTask(File file, String title, List<SectionInfo> sections)
	{
	    _file = file;
	    _title = title;
	    _sections = sections;
	}
	
	@Override
	public Void call() throws Exception
	{
	    try (FileOutputStream out = new FileOutputStream(_file)) {
		new PaperGenerator().generate(_title, _sections, out);
	    }
	    return null;
	}
    }
    
    private void setStatus(String s)
    {
	_statusLabel.setText(s);
    }
	    
    @FXML
    private void handleRemoveSection(ActionEvent event)
    {
	_removing = true;
	_sections.remove(_ix);
	_sectionsNames.remove(_ix);
    }

    @FXML
    private void handleAddSection(ActionEvent event)
    {
	_sectionsNames.add(_newSection.getName());
	_sections.add(_newSection);
	_sectionsLV.getSelectionModel().selectLast();
    }
    
    private class SectionUpdater implements ChangeListener<Number>
    {
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
	{
	    if (_removing) {
		_ix = newValue.intValue();
		if (_ix != -1) loadSection(_sections.get(_ix));
		_removing = false;
	    }
	    else if (newValue.intValue() != -1) {
		if (_ix != -1) _sections.set(_ix, saveSection());
		_ix = newValue.intValue();
		loadSection(_sections.get(_ix));
	    }
	    for (Control c : _sectionControls) c.setDisable(_ix == -1);
	} 	
    }
    
    private SectionInfo saveSection()
    {
	List<String> tags = Arrays.asList(_sectionTagsTF.getText().split("\\s+"));
	java.awt.Color headerColor = new java.awt.Color((float)_sectionHeaderColorPicker.getValue().getRed(),
		(float)_sectionHeaderColorPicker.getValue().getGreen(), (float)_sectionHeaderColorPicker.getValue().getBlue());
	return new SectionInfo(tags, _sectionNameTF.getText(), headerColor, _sectionSudokuCB.isSelected());
    }

    private void loadSection(SectionInfo si)
    {
	_sectionNameTF.setText(si.getName());
	StringBuilder tagsSB = new StringBuilder();
	for (String tag : si.getTags()) {
	    if (tagsSB.length() > 0) tagsSB.append(" ");
	    tagsSB.append(tag);
	}
	_sectionTagsTF.setText(tagsSB.toString());
	_sectionHeaderColorPicker.setValue(javafx.scene.paint.Color.rgb(si.getHeaderColor().getRed(),
	    si.getHeaderColor().getGreen(), si.getHeaderColor().getBlue()));
	_sectionHeaderColorPicker.fireEvent(new ActionEvent(null, _sectionHeaderColorPicker));
	_sectionSudokuCB.setSelected(si.getSudoku());
    }        
}
