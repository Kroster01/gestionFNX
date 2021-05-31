package pet.vo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CompFXEjecucionVO {

	@FXML
    private CheckBox cModelo2;
	   
	@FXML
    private TextField descripcion;
	
    @FXML
    private ComboBox<EstadosVO> estado;

    @FXML
    private TableColumn<PeticionesVO, Integer> colIdPet;

    @FXML
    private Button bPathRTC;

    @FXML
    private TableColumn<PeticionesVO, String> colEstado;

    @FXML
    private Button bEntregar;

    @FXML
    private TableColumn<PeticionesVO, String> colDSolution;

    @FXML
    private TextField responsableStgo;

    @FXML
    private TableColumn<PeticionesVO, String> colDescripcion;

    @FXML
    private Button bGuardar;

    @FXML
    private TableColumn<PeticionesVO, String> colDCentro;

    @FXML
    private TextField responsableCentro;

    @FXML
    private TextField tAplazadas;

    @FXML
    private Button bActualizar;

    @FXML
    private TableColumn<PeticionesVO, String> colTipPet;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private TextField tPeticiones3;

    @FXML
    private TableColumn<PeticionesVO, String> colRespStgo;

    @FXML
    private TextField tPeticiones5;

    @FXML
    private TextField repSolution;

    @FXML
    private Button bBuscar;

    @FXML
    private TextField repCentro;

    @FXML
    private TextField tPeticionesHoy;

    @FXML
    private TableColumn<PeticionesVO, String> colCodDemanda;

    @FXML
    private TextField incidencias;

    @FXML
    private DatePicker fechaKiuwan;

    @FXML
    private TextField repRTC;

    @FXML
    private Pane principal;

    @FXML
    private TextField dudas;

    @FXML
    private TableColumn<PeticionesVO, String> colResponsableCentro;

    @FXML
    private TableView<PeticionesVO> tablePeticiones;

    @FXML
    private Button bBorrar;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private TableColumn<PeticionesVO, String> colPPMEtapa;

    @FXML
    private DatePicker ppmFecha;

    @FXML
    private TableColumn<PeticionesVO, String> colPPMFecha;

    @FXML
    private TableColumn<PeticionesVO, String> colRespCentro;

    @FXML
    private TextField tEjecucion;

    @FXML
    private TextField idPeticion;

    @FXML
    private TextField ncs;

    @FXML
    private TableColumn<PeticionesVO, String> colNCs;

    @FXML
    private TextField tBusqueda;

    @FXML
    private TextField tEntregadas;

    @FXML
    private TextField idDemanda;

    @FXML
    private TextField esfuerzo;

    @FXML
    private ComboBox<EtapaPPMVO> etapaPPM;
    
    @FXML
    private ComboBox<EtapaPPMVO> etapaPeticion;

    @FXML
    private TableColumn<PeticionesVO, String> colDudas;

    @FXML
    private Button bPathSolution;

    @FXML
    private Button bDetalle;

    @FXML
    private ComboBox<TipoPeticionVO> tipoPeticion;

    @FXML
    private Button bPathCentro;

	public TextField getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(TextField descripcion) {
		this.descripcion = descripcion;
	}

	public ComboBox<EstadosVO> getEstado() {
		return estado;
	}

	public void setEstado(ComboBox<EstadosVO> estado) {
		this.estado = estado;
	}

	public TableColumn<PeticionesVO, Integer> getColIdPet() {
		return colIdPet;
	}

	public void setColIdPet(TableColumn<PeticionesVO, Integer> colIdPet) {
		this.colIdPet = colIdPet;
	}

	public Button getbPathRTC() {
		return bPathRTC;
	}

	public void setbPathRTC(Button bPathRTC) {
		this.bPathRTC = bPathRTC;
	}

	public TableColumn<PeticionesVO, String> getColEstado() {
		return colEstado;
	}

	public void setColEstado(TableColumn<PeticionesVO, String> colEstado) {
		this.colEstado = colEstado;
	}

	public Button getbEntregar() {
		return bEntregar;
	}

	public void setbEntregar(Button bEntregar) {
		this.bEntregar = bEntregar;
	}

	public TableColumn<PeticionesVO, String> getColDSolution() {
		return colDSolution;
	}

	public void setColDSolution(TableColumn<PeticionesVO, String> colDSolution) {
		this.colDSolution = colDSolution;
	}

	public TextField getResponsableStgo() {
		return responsableStgo;
	}

	public void setResponsableStgo(TextField responsableStgo) {
		this.responsableStgo = responsableStgo;
	}

	public TableColumn<PeticionesVO, String> getColDescripcion() {
		return colDescripcion;
	}

	public void setColDescripcion(TableColumn<PeticionesVO, String> colDescripcion) {
		this.colDescripcion = colDescripcion;
	}

	public Button getbGuardar() {
		return bGuardar;
	}

	public void setbGuardar(Button bGuardar) {
		this.bGuardar = bGuardar;
	}

	public TableColumn<PeticionesVO, String> getColDCentro() {
		return colDCentro;
	}

	public void setColDCentro(TableColumn<PeticionesVO, String> colDCentro) {
		this.colDCentro = colDCentro;
	}

	public TextField getResponsableCentro() {
		return responsableCentro;
	}

	public void setResponsableCentro(TextField responsableCentro) {
		this.responsableCentro = responsableCentro;
	}

	public TextField gettAplazadas() {
		return tAplazadas;
	}

	public void settAplazadas(TextField tAplazadas) {
		this.tAplazadas = tAplazadas;
	}

	public Button getbActualizar() {
		return bActualizar;
	}

	public void setbActualizar(Button bActualizar) {
		this.bActualizar = bActualizar;
	}

	public TableColumn<PeticionesVO, String> getColTipPet() {
		return colTipPet;
	}

	public void setColTipPet(TableColumn<PeticionesVO, String> colTipPet) {
		this.colTipPet = colTipPet;
	}

	public DatePicker getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(DatePicker fechaFin) {
		this.fechaFin = fechaFin;
	}

	public TextField gettPeticiones3() {
		return tPeticiones3;
	}

	public void settPeticiones3(TextField tPeticiones3) {
		this.tPeticiones3 = tPeticiones3;
	}

	public TableColumn<PeticionesVO, String> getColRespStgo() {
		return colRespStgo;
	}

	public void setColRespStgo(TableColumn<PeticionesVO, String> colRespStgo) {
		this.colRespStgo = colRespStgo;
	}

	public TextField gettPeticiones5() {
		return tPeticiones5;
	}

	public void settPeticiones5(TextField tPeticiones5) {
		this.tPeticiones5 = tPeticiones5;
	}

	public TextField getRepSolution() {
		return repSolution;
	}

	public void setRepSolution(TextField repSolution) {
		this.repSolution = repSolution;
	}

	public Button getbBuscar() {
		return bBuscar;
	}

	public void setbBuscar(Button bBuscar) {
		this.bBuscar = bBuscar;
	}

	public TextField getRepCentro() {
		return repCentro;
	}

	public void setRepCentro(TextField repCentro) {
		this.repCentro = repCentro;
	}

	public TextField gettPeticionesHoy() {
		return tPeticionesHoy;
	}

	public void settPeticionesHoy(TextField tPeticionesHoy) {
		this.tPeticionesHoy = tPeticionesHoy;
	}

	public TableColumn<PeticionesVO, String> getColCodDemanda() {
		return colCodDemanda;
	}

	public void setColCodDemanda(TableColumn<PeticionesVO, String> colCodDemanda) {
		this.colCodDemanda = colCodDemanda;
	}

	public TextField getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(TextField incidencias) {
		this.incidencias = incidencias;
	}

	public DatePicker getFechaKiuwan() {
		return fechaKiuwan;
	}

	public void setFechaKiuwan(DatePicker fechaKiuwan) {
		this.fechaKiuwan = fechaKiuwan;
	}

	public TextField getRepRTC() {
		return repRTC;
	}

	public void setRepRTC(TextField repRTC) {
		this.repRTC = repRTC;
	}

	public Pane getPrincipal() {
		return principal;
	}

	public void setPrincipal(Pane principal) {
		this.principal = principal;
	}

	public TextField getDudas() {
		return dudas;
	}

	public void setDudas(TextField dudas) {
		this.dudas = dudas;
	}

	public TableColumn<PeticionesVO, String> getColResponsableCentro() {
		return colResponsableCentro;
	}

	public void setColResponsableCentro(TableColumn<PeticionesVO, String> colResponsableCentro) {
		this.colResponsableCentro = colResponsableCentro;
	}

	public TableView<PeticionesVO> getTablePeticiones() {
		return tablePeticiones;
	}

	public void setTablePeticiones(TableView<PeticionesVO> tablePeticiones) {
		this.tablePeticiones = tablePeticiones;
	}

	public Button getbBorrar() {
		return bBorrar;
	}

	public void setbBorrar(Button bBorrar) {
		this.bBorrar = bBorrar;
	}

	public DatePicker getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(DatePicker fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public TableColumn<PeticionesVO, String> getColPPMEtapa() {
		return colPPMEtapa;
	}

	public void setColPPMEtapa(TableColumn<PeticionesVO, String> colPPMEtapa) {
		this.colPPMEtapa = colPPMEtapa;
	}

	public DatePicker getPpmFecha() {
		return ppmFecha;
	}

	public void setPpmFecha(DatePicker ppmFecha) {
		this.ppmFecha = ppmFecha;
	}

	public TableColumn<PeticionesVO, String> getColPPMFecha() {
		return colPPMFecha;
	}

	public void setColPPMFecha(TableColumn<PeticionesVO, String> colPPMFecha) {
		this.colPPMFecha = colPPMFecha;
	}

	public TableColumn<PeticionesVO, String> getColRespCentro() {
		return colRespCentro;
	}

	public void setColRespCentro(TableColumn<PeticionesVO, String> colRespCentro) {
		this.colRespCentro = colRespCentro;
	}

	public TextField gettEjecucion() {
		return tEjecucion;
	}

	public void settEjecucion(TextField tEjecucion) {
		this.tEjecucion = tEjecucion;
	}

	public TextField getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(TextField idPeticion) {
		this.idPeticion = idPeticion;
	}

	public TextField getNcs() {
		return ncs;
	}

	public void setNcs(TextField ncs) {
		this.ncs = ncs;
	}

	public TableColumn<PeticionesVO, String> getColNCs() {
		return colNCs;
	}

	public void setColNCs(TableColumn<PeticionesVO, String> colNCs) {
		this.colNCs = colNCs;
	}

	public TextField gettBusqueda() {
		return tBusqueda;
	}

	public void settBusqueda(TextField tBusqueda) {
		this.tBusqueda = tBusqueda;
	}

	public TextField gettEntregadas() {
		return tEntregadas;
	}

	public void settEntregadas(TextField tEntregadas) {
		this.tEntregadas = tEntregadas;
	}

	public TextField getIdDemanda() {
		return idDemanda;
	}

	public void setIdDemanda(TextField idDemanda) {
		this.idDemanda = idDemanda;
	}

	public TextField getEsfuerzo() {
		return esfuerzo;
	}

	public void setEsfuerzo(TextField esfuerzo) {
		this.esfuerzo = esfuerzo;
	}

	public ComboBox<EtapaPPMVO> getEtapaPPM() {
		return etapaPPM;
	}

	public void setEtapaPPM(ComboBox<EtapaPPMVO> etapaPPM) {
		this.etapaPPM = etapaPPM;
	}

	public TableColumn<PeticionesVO, String> getColDudas() {
		return colDudas;
	}

	public void setColDudas(TableColumn<PeticionesVO, String> colDudas) {
		this.colDudas = colDudas;
	}

	public Button getbPathSolution() {
		return bPathSolution;
	}

	public void setbPathSolution(Button bPathSolution) {
		this.bPathSolution = bPathSolution;
	}

	public Button getbDetalle() {
		return bDetalle;
	}

	public void setbDetalle(Button bDetalle) {
		this.bDetalle = bDetalle;
	}

	public ComboBox<TipoPeticionVO> getTipoPeticion() {
		return tipoPeticion;
	}

	public void setTipoPeticion(ComboBox<TipoPeticionVO> tipoPeticion) {
		this.tipoPeticion = tipoPeticion;
	}

	public Button getbPathCentro() {
		return bPathCentro;
	}

	public void setbPathCentro(Button bPathCentro) {
		this.bPathCentro = bPathCentro;
	}

	public ComboBox<EtapaPPMVO> getEtapaPeticion() {
		return etapaPeticion;
	}

	public void setEtapaPeticion(ComboBox<EtapaPPMVO> etapaPeticion) {
		this.etapaPeticion = etapaPeticion;
	}

	public CheckBox getcModelo2() {
		return cModelo2;
	}

	public void setcModelo2(CheckBox cModelo2) {
		this.cModelo2 = cModelo2;
	}
}
