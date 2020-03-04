package com.fauzan.sunmilib;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.sunmi.printerhelper.utils.AidlUtil;
import com.sunmi.printerhelper.utils.ESCUtil;

public class EzSunmi {

    private Context mContext;

    private static volatile EzSunmi instance;

    private EzSunmi(Context context) {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        this.mContext = context;
        AidlUtil.getInstance().connectPrinterService(context);
        AidlUtil.getInstance().initPrinter();
    }

    public static EzSunmi getInstance(Context context) {
        //Double check locking pattern
        if (instance == null) { //Check for the first time
            synchronized (EzSunmi.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (instance == null)
                    instance = new EzSunmi(context);
            }
        }

        return instance;
    }

    public static void printText(String text, int size) {
        AidlUtil.getInstance().printText(text, size, false, false);
    }

    public static void printText(String text, int size, Boolean isBold, Boolean isUnderLine) {
        AidlUtil.getInstance().printText(text, size, isBold, isUnderLine);
    }

    public static void printText(String text, int size, Boolean isBold) {
        AidlUtil.getInstance().printText(text, size, isBold, false);
    }


    public static void printQr(String text, int size) {
        AidlUtil.getInstance().printQr(text, size, 3);
    }

    public static void addLine(int line) {
        AidlUtil.getInstance().sendRawData(ESCUtil.nextLine(line));
    }

    public static void addSplitter(int size) {
        AidlUtil.getInstance().printText("----------------------------------", size, false, false);
    }

    public static void setAlignment(Alignment alignment) {
        if (alignment == Alignment.LEFT)
            AidlUtil.getInstance().setAlignment(0);
        else if (alignment == Alignment.CENTER)
            AidlUtil.getInstance().setAlignment(1);
        else
            AidlUtil.getInstance().setAlignment(2);
    }

    public static void printLogo(int image) {
        AidlUtil.getInstance().printBitmap(BitmapFactory.decodeResource(instance.mContext.getResources(), image));
    }

    public static void printLogo(int image, int orientation) {
        AidlUtil.getInstance().printBitmap(BitmapFactory.decodeResource(instance.mContext.getResources(), image), orientation);
    }


}
