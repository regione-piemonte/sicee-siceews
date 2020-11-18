/*******************************************************************************
 * SPDX-License-Identifier: EUPL-1.2
 * Copyright Regione Piemonte - 2020
 *******************************************************************************/
package it.csi.sicee.siceews.integration.db;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SICEE_T_CREDITO_2018 database table.
 * 
 */
@Entity
@Table(name="SICEE_T_CREDITO_2018")
@NamedQuery(name="SiceeTCredito2018.findAll", query="SELECT s FROM SiceeTCredito2018 s")
public class SiceeTCredito2018 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="pippo", sequenceName="SEQ_SICEE_T_CREDITO_2018", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pippo")
	@Column(name="ID_CREDITO")
	private long idCredito;

	private String anno;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_OPERAZIONE")
	private Date dataOperazione;

	@Column(name="FK_CERTIFICATORE")
	private String fkCertificatore;

	@Column(name="FK_CERTIFICATORE_CERTIFICATO")
	private String fkCertificatoreCertificato;

	@Column(name="FK_TIPO_OP_2018")
	private Integer fkTipoOp2018;

	@Column(name="FK_TRANSAZIONE_2018")
	private Integer fkTransazione2018;

	private String note;

	@Column(name="PROGR_CERTIFICATO")
	private String progrCertificato;

	@Column(name="VALORE_CREDITO_FINALE")
	private BigDecimal valoreCreditoFinale;

	@Column(name="VALORE_CREDITO_INIZIALE")
	private BigDecimal valoreCreditoIniziale;

	@Column(name="VALORE_OPERAZIONE")
	private BigDecimal valoreOperazione;

	public SiceeTCredito2018() {
	}

	public long getIdCredito() {
//		System.out.println("####################");
//		System.out.println("Stampo get l'ID_CREDITO: "+idCredito);
//		System.out.println("####################");
		return this.idCredito;
	}

	public void setIdCredito(long idCredito) {
//		System.out.println("####################");
//		System.out.println("Stampo set l'ID_CREDITO: "+idCredito);
//		System.out.println("####################");
		this.idCredito = idCredito;
	}

	public String getAnno() {
		return this.anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public Date getDataOperazione() {
		return this.dataOperazione;
	}

	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}

	public String getFkCertificatore() {
		return this.fkCertificatore;
	}

	public void setFkCertificatore(String fkCertificatore) {
		this.fkCertificatore = fkCertificatore;
	}

	public String getFkCertificatoreCertificato() {
		return this.fkCertificatoreCertificato;
	}

	public void setFkCertificatoreCertificato(String fkCertificatoreCertificato) {
		this.fkCertificatoreCertificato = fkCertificatoreCertificato;
	}

	public Integer getFkTipoOp2018() {
		return this.fkTipoOp2018;
	}

	public void setFkTipoOp2018(Integer fkTipoOp2018) {
		this.fkTipoOp2018 = fkTipoOp2018;
	}

	public Integer getFkTransazione2018() {
		return this.fkTransazione2018;
	}

	public void setFkTransazione2018(Integer fkTransazione2018) {
		this.fkTransazione2018 = fkTransazione2018;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getProgrCertificato() {
		return this.progrCertificato;
	}

	public void setProgrCertificato(String progrCertificato) {
		this.progrCertificato = progrCertificato;
	}

	public BigDecimal getValoreCreditoFinale() {
		return this.valoreCreditoFinale;
	}

	public void setValoreCreditoFinale(BigDecimal valoreCreditoFinale) {
		this.valoreCreditoFinale = valoreCreditoFinale;
	}

	public BigDecimal getValoreCreditoIniziale() {
		return this.valoreCreditoIniziale;
	}

	public void setValoreCreditoIniziale(BigDecimal valoreCreditoIniziale) {
		this.valoreCreditoIniziale = valoreCreditoIniziale;
	}

	public BigDecimal getValoreOperazione() {
		return this.valoreOperazione;
	}

	public void setValoreOperazione(BigDecimal valoreOperazione) {
		this.valoreOperazione = valoreOperazione;
	}

}