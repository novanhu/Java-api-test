package com.ice.happypass.common;
import org.apache.commons.codec.binary.Base64;

public class Base64Decoder {


    public static String association_uid(String associationUID) {

        String associationUID_FromAPI = associationUID;

// Get bytes from string

        byte[] byteArray = Base64.decodeBase64(associationUID_FromAPI.getBytes());

        String associationId = new String(byteArray);

        String stringsplit = associationUID_FromAPI + " = " + associationId;
        String[] id_decoded = stringsplit.split(":");
        String associationid_decoded = id_decoded[1];
        System.out.print(associationid_decoded);
        return associationid_decoded;
    }
    // convert evidence UID from API to evidenceID
    public static String evidence_uid(String evidenceUID) {

        String evidenceUID_FromAPI = evidenceUID;
        byte[] byteArray = Base64.decodeBase64(evidenceUID_FromAPI.getBytes());
        String associationId = new String(byteArray);

        String stringsplit = evidenceUID_FromAPI + " = " + associationId;
        String[] id_decoded = stringsplit.split(":");
        String evidenceid_decoded = id_decoded[1];
        System.out.print(evidenceid_decoded);
        return evidenceid_decoded;
    }
}
