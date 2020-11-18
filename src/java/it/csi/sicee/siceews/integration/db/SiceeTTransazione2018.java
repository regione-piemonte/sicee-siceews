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
 * The persistent class for the SICEE_T_TRANSAZIONE_2018 database table.
 * 
 */
@Entity
@Table(name="SICEE_T_TRANSAZIONE_2018")
public class SiceeTTransazione2018 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_CHIUSURA_TRANSAZIONE")
	private Date dtChiusuraTransazione;

	@Column(name="FK_CERTIFICATORE")
	private String fkCertificatore;

	@Column(name="FK_STATO_TRANS_2018")
	private Integer fkStatoTrans2018;

	@Id
	@Column(name="ID_TRANSAZIONE_2018")
	private Integer idTransazione2018;

	@Temporal(TemporalType.DATE)
	@Column(name="OP_GETSTATOTRANS_DT")
	private Date opGetstatotransDt;

	@Column(name="OP_GETSTATOTRANS_FK_STATO")
	private Integer opGetstatotransFkStato;

	@Column(name="S1_ANAGRAFICA_VERSANTE")
	private String s1AnagraficaVersante;

	@Column(name="S1_COD_ID_VERSANTE")
	private String s1CodIdVersante;

	@Temporal(TemporalType.DATE)
	@Column(name="S1_DT_CREAZIONE_TRANSAZIONE")
	private Date s1DtCreazioneTransazione;

	@Column(name="S1_ID_TRANSAZIONE_MDP")
	private String s1IdTransazioneMdp;

	@Column(name="S1_TIPO_ID_VERSANTE")
	private String s1TipoIdVersante;

	@Column(name="S1_VALORE_TRANSAZIONE")
	private BigDecimal s1ValoreTransazione;

	@Column(name="S2_DESCR_SERVIZIO_PSP")
	private String s2DescrServizioPsp;

	@Column(name="S2_ID_INFORMATIVA_PSP")
	private BigDecimal s2IdInformativaPsp;

	@Column(name="S2_IDENTIFICATIVO_PSP")
	private String s2IdentificativoPsp;

	@Column(name="S2_RAG_SOCIALE_PSP")
	private String s2RagSocialePsp;

	@Temporal(TemporalType.DATE)
	@Column(name="S3_DT_AVVIO_TRANSAZIONE")
	private Date s3DtAvvioTransazione;

	@Column(name="S3_ID_IUV")
	private String s3IdIuv;

	@Lob
	@Column(name="XML_RT")
	private String xmlRt;

	public SiceeTTransazione2018() {
	}

	public Date getDtChiusuraTransazione() {
		return this.dtChiusuraTransazione;
	}

	public void setDtChiusuraTransazione(Date dtChiusuraTransazione) {
		this.dtChiusuraTransazione = dtChiusuraTransazione;
	}

	public String getFkCertificatore() {
		return this.fkCertificatore;
	}

	public void setFkCertificatore(String fkCertificatore) {
		this.fkCertificatore = fkCertificatore;
	}

	public Integer getFkStatoTrans2018() {
		return this.fkStatoTrans2018;
	}

	public void setFkStatoTrans2018(Integer fkStatoTrans2018) {
		this.fkStatoTrans2018 = fkStatoTrans2018;
	}

	public Integer getIdTransazione2018() {
		return this.idTransazione2018;
	}

	public void setIdTransazione2018(Integer idTransazione2018) {
		this.idTransazione2018 = idTransazione2018;
	}

	public Date getOpGetstatotransDt() {
		return this.opGetstatotransDt;
	}

	public void setOpGetstatotransDt(Date opGetstatotransDt) {
		this.opGetstatotransDt = opGetstatotransDt;
	}

	public Integer getOpGetstatotransFkStato() {
		return this.opGetstatotransFkStato;
	}

	public void setOpGetstatotransFkStato(Integer opGetstatotransFkStato) {
		this.opGetstatotransFkStato = opGetstatotransFkStato;
	}

	public String getS1AnagraficaVersante() {
		return this.s1AnagraficaVersante;
	}

	public void setS1AnagraficaVersante(String s1AnagraficaVersante) {
		this.s1AnagraficaVersante = s1AnagraficaVersante;
	}

	public String getS1CodIdVersante() {
		return this.s1CodIdVersante;
	}

	public void setS1CodIdVersante(String s1CodIdVersante) {
		this.s1CodIdVersante = s1CodIdVersante;
	}

	public Date getS1DtCreazioneTransazione() {
		return this.s1DtCreazioneTransazione;
	}

	public void setS1DtCreazioneTransazione(Date s1DtCreazioneTransazione) {
		this.s1DtCreazioneTransazione = s1DtCreazioneTransazione;
	}

	public String getS1IdTransazioneMdp() {
		return this.s1IdTransazioneMdp;
	}

	public void setS1IdTransazioneMdp(String s1IdTransazioneMdp) {
		this.s1IdTransazioneMdp = s1IdTransazioneMdp;
	}

	public String getS1TipoIdVersante() {
		return this.s1TipoIdVersante;
	}

	public void setS1TipoIdVersante(String s1TipoIdVersante) {
		this.s1TipoIdVersante = s1TipoIdVersante;
	}

	public BigDecimal getS1ValoreTransazione() {
		return this.s1ValoreTransazione;
	}

	public void setS1ValoreTransazione(BigDecimal s1ValoreTransazione) {
		this.s1ValoreTransazione = s1ValoreTransazione;
	}

	public String getS2DescrServizioPsp() {
		return this.s2DescrServizioPsp;
	}

	public void setS2DescrServizioPsp(String s2DescrServizioPsp) {
		this.s2DescrServizioPsp = s2DescrServizioPsp;
	}

	public BigDecimal getS2IdInformativaPsp() {
		return this.s2IdInformativaPsp;
	}

	public void setS2IdInformativaPsp(BigDecimal s2IdInformativaPsp) {
		this.s2IdInformativaPsp = s2IdInformativaPsp;
	}

	public String getS2IdentificativoPsp() {
		return this.s2IdentificativoPsp;
	}

	public void setS2IdentificativoPsp(String s2IdentificativoPsp) {
		this.s2IdentificativoPsp = s2IdentificativoPsp;
	}

	public String getS2RagSocialePsp() {
		return this.s2RagSocialePsp;
	}

	public void setS2RagSocialePsp(String s2RagSocialePsp) {
		this.s2RagSocialePsp = s2RagSocialePsp;
	}

	public Date getS3DtAvvioTransazione() {
		return this.s3DtAvvioTransazione;
	}

	public void setS3DtAvvioTransazione(Date s3DtAvvioTransazione) {
		this.s3DtAvvioTransazione = s3DtAvvioTransazione;
	}

	public String getS3IdIuv() {
		return this.s3IdIuv;
	}

	public void setS3IdIuv(String s3IdIuv) {
		this.s3IdIuv = s3IdIuv;
	}

	public String getXmlRt() {
		return this.xmlRt;
	}

	public void setXmlRt(String xmlRt) {
		this.xmlRt = xmlRt;
	}

}