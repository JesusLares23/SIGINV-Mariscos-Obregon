/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacion_mariscos.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 *
 * @author demib
 */
public class GeneradorQR {
    public static void generarQR(String contenido, String rutaSalida) throws Exception {
        File carpeta = new File("qrs");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(contenido, BarcodeFormat.QR_CODE, 200, 200);
        Path path = FileSystems.getDefault().getPath(rutaSalida);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        System.out.println("QR generado: " + rutaSalida);
    }
    
//    public static void main(String[] args) throws Exception {
//        // genera un QR por cada insumo con su nombre como contenido
//        generarQR("Camarón",        "qrs/camaron.png");
//        generarQR("Pulpo",          "qrs/pulpo.png");
//        generarQR("Filete de pescado", "qrs/filete.png");
//        generarQR("Ostión",         "qrs/ostion.png");
//        generarQR("Limón",          "qrs/limon.png");
//        generarQR("Cebolla morada", "qrs/cebolla.png");
//        generarQR("Chile serrano",  "qrs/chile.png");
//        generarQR("Aguacate",       "qrs/aguacate.png");
//        generarQR("Salsa inglesa",  "qrs/salsa.png");
//        generarQR("Catsup",         "qrs/catsup.png");
//        generarQR("Tostadas",       "qrs/tostadas.png");
//        generarQR("Refresco",       "qrs/refresco.png");
//    }
}
