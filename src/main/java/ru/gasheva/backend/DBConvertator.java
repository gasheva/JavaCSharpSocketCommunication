package ru.gasheva.backend;

import ru.gasheva.database.NormDao;
import ru.gasheva.database.NotNormDao;

import java.util.List;

public class DBConvertator {
    public static boolean writeToNormDB(NotNormDao notNormDao, NormDao normDao){
        boolean isAllRight=true;
        try {
            List<NotNormEntity> notNormEntities = notNormDao.read();
            notNormEntities.forEach(normDao::createGallery);
            notNormEntities.forEach(normDao::createChecker);
            notNormEntities.forEach(normDao::createArtist);
            notNormEntities.forEach(normDao::createPaint);
            notNormEntities.forEach(normDao::createPaintChecking);
            System.out.println("Writing to DB...");
        }catch (Exception e){
            isAllRight = false;
        }
        return isAllRight;
    }
}
