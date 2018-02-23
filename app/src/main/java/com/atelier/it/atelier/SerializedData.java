package com.atelier.it.atelier;

import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexander Villegas on 15/02/2018.
 */

public class SerializedData {

    public class response{

        @SerializedName("btnLogin")
        private String btnLogin;

        @SerializedName("btnInvitado")
        private String btnInvitado;

        @SerializedName("btnHuesped")
        private String btnHuesped;

        @SerializedName("txtapellido")
        private String txtapellido;

        @SerializedName("txtfecha")
        private String txtfecha;

        @SerializedName("txtreservacion")
        private String txtreservacion;

        @SerializedName("lblsaludo")
        private String lblsaludo;

        @SerializedName("lblhabitacion")
        private String lblhabitacion;

        @SerializedName("btn_llave")
        private String btn_llave;

        @SerializedName("btn_cuenta")
        private String btn_cuenta;

        @SerializedName("btn_tienda")
        private String btn_tienda;

        @SerializedName("btn_restaurantes")
        private String btn_restaurantes;

        @SerializedName("btn_tv")
        private String btn_tv;

        @SerializedName("btn_mapa")
        private String btn_mapa;

        @SerializedName("btn_resortservice")
        private String btn_resortservice;

        @SerializedName("btn_roomservice")
        private String btn_roomservice;


        public void setBtnLogin(String btnLogin){
            this.btnLogin = btnLogin;
        }
        public String getBtnLogin(){
            return btnLogin;
        }

        public void setBtnInvitado(String btnInvitado){
            this.btnInvitado = btnInvitado;
        }
        public String getBtnInvitado(){
            return btnInvitado;
        }

        public void setBtnHuesped(String btnHuesped){
            this.btnHuesped = btnHuesped;
        }
        public String getBtnHuesped(){
            return btnHuesped;
        }

        public void setTxtapellido(String txtapellido){
            this.txtapellido = txtapellido;
        }
        public String getTxtapellido(){
            return txtapellido;
        }

        public void setTxtfecha(String txtfecha){
            this.txtfecha = txtfecha;
        }
        public String getTxtfecha(){
            return txtfecha;
        }

        public void setTxtreservacion(String txtreservacion){
            this.txtreservacion = txtreservacion;
        }
        public String getTxtreservacion(){
            return txtreservacion;
        }

        public void setLblsaludo(String lblsaludo){
            this.lblsaludo = lblsaludo;
        }
        public String getLblsaludo(){
            return lblsaludo;
        }

        public void setLblhabitacion(String lblhabitacion){
            this.lblhabitacion = lblhabitacion;
        }
        public String getLblhabitacion(){
            return lblhabitacion;
        }

        public void setBtn_llave(String btn_llave){
            this.btn_llave = btn_llave;
        }

        public String getBtn_llave(){
            return btn_llave;
        }

        public void setBtn_cuenta(String btn_cuenta){
            this.btn_cuenta = btn_cuenta;
        }

        public String getBtn_cuenta(){
            return btn_cuenta;
        }

        public void setBtn_tienda(String btn_tienda){
            this.btn_tienda = btn_tienda;
        }
        public String getBtn_tienda(){
            return btn_tienda;
        }

        public void setBtn_restaurantes(String btn_restaurantes){
            this.btn_restaurantes = btn_restaurantes;
        }

        public String getBtn_restaurantes(){
            return btn_restaurantes;
        }

        public void setBtn_tv(String btn_tv){
            this.btn_tv = btn_tv;
        }
        public String getBtn_tv(){
            return btn_tv;
        }

        public void setBtn_mapa(String btn_mapa){
            this.btn_mapa = btn_mapa;
        }
        public String getBtn_mapa(){
            return btn_mapa;
        }

        public void setBtn_resortservice(String btn_resortservice){
            this.btn_resortservice = btn_resortservice;
        }

        public String getBtn_resortservice(){
            return btn_resortservice;
        }

        public void setBtn_roomservice(String btn_roomservice){
            this.btn_roomservice = btn_roomservice;
        }
        public String getBtn_roomservice(){
            return btn_roomservice;
        }

       /* @Override
        public String toString(){
            return "response{"+
                    "btnLogin=" + btnLogin+'\''+
                    "btnInvitado="+btnInvitado+'\''+
                    "btnHuesped="+ btnHuesped+'\''+
                    "txtapellido="+ txtapellido+'\''+
                    "txtfecha="+txtfecha+'\''+
                    "txtreservacion="+txtreservacion+'\''+
                    "lblsaludo="+lblsaludo+'\''+
                    "lblhabitacion="+lblhabitacion+'\''+
                    "btn_llave="+btn_llave+'\''+
                    "btn_cuenta="+btn_cuenta+'\''+
                    "btn_tienda="+btn_tienda+'\''+
                    "btn_restaurantes="+btn_restaurantes+'\''+
                    "btn_tv="+btn_tv+'\''+
                    "btn_mapa="+btn_mapa+'\''+
                    "btn_resortservice="+btn_resortservice+'\''+
                    "btn_roomservice="+btn_roomservice+'\''+
                    '}';
        }*/

    }

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
    public void setMassage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

}
