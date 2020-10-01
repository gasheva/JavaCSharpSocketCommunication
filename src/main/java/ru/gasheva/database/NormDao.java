package ru.gasheva.database;

import com.google.gson.Gson;
import ru.gasheva.backend.Message;
import ru.gasheva.backend.MessagePart;
import ru.gasheva.backend.NotNormEntity;
import ru.gasheva.backend.normEntities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class NormDao {
    private DataSource dataSource = new DataSource("src/main/resources/JdbcConfig.properties");
    public NormDao(){
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("DELETE FROM paint_checking;");
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM paint;");
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM artists;");
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM gallery;");
            statement.executeUpdate();
            statement = conn.prepareStatement("DELETE FROM checker;");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<GalleryEntity> readGalleries(){
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT id, name, location FROM gallery");
            List<GalleryEntity> galleryEntities = new LinkedList<>();
            GalleryEntity gallery = new GalleryEntity();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                gallery.setId(rs.getInt("id"));
                gallery.setName(rs.getString("name"));
                gallery.setLocation(rs.getString("location"));
                galleryEntities.add(gallery);
            }
            return galleryEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<ArtistEntity> readArtists(){
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT id, fio, b_city, have_awards\n" +
                    "FROM artists;");
            List<ArtistEntity> artistEntities = new LinkedList<>();
            ArtistEntity artist = new ArtistEntity();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                artist.setId(rs.getInt("id"));
                artist.setFio(rs.getString("fio"));
                artist.setB_city(rs.getString("b_city"));
                artist.setHave_awards(rs.getBoolean("have_awards"));
                artistEntities.add(artist);
            }
            return artistEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<PaintEntity> readPaints(){
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT id, name, price, id_artist\n" +
                    "FROM paint;");
            List<PaintEntity> paintEntities = new LinkedList<>();
            PaintEntity paint = new PaintEntity();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                paint.setId(rs.getInt("id"));
                paint.setName(rs.getString("name"));
                paint.setId_artist(rs.getInt("id_artist"));
                paint.setPrice(rs.getInt("price"));
                paintEntities.add(paint);
            }
            return paintEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<PaintCheckingEntity> readPaintCheckings(){
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT id, check_date, id_checker, id_paint, id_gal, need_rest, marks\n" +
                    "FROM paint_checking;");
            List<PaintCheckingEntity> paintCheckingEntities = new LinkedList<>();
            PaintCheckingEntity paintChecking = new PaintCheckingEntity();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                paintChecking.setId(rs.getInt("id"));
                paintChecking.setCheck_date(((Date)rs.getObject("check_date")).toLocalDate());
                paintChecking.setId_checker(rs.getInt("id_checker"));
                paintChecking.setId_gal(rs.getInt("id_gal"));
                paintChecking.setMarks(rs.getString("marks"));
                paintChecking.setNeed_rest(rs.getBoolean("need_rest"));
                paintCheckingEntities.add(paintChecking);
            }
            return paintCheckingEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CheckerEntity> readCheckers(){
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("SELECT id, name, start_year\n" +
                    "FROM checker;");
            List<CheckerEntity> checkerEntities = new LinkedList<>();
            CheckerEntity checkerEntity = new CheckerEntity();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                checkerEntity.setId(rs.getInt("id"));
                checkerEntity.setStart_year(((Date)rs.getObject("start_year")).toLocalDate());
                checkerEntity.setName(rs.getString("name"));
                checkerEntities.add(checkerEntity);
            }
            return checkerEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MessagePart> readRequestForXML(){
        Gson gson = new Gson();
        try(Connection conn=dataSource.getConnection()){
            //получаем все проверки
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT paint_checking.check_date, checker.name as checker_name, need_rest, paint.id, paint.name as paint_name, artists.fio, gallery.name as gallery_name FROM paint_checking\n" +
                    "JOIN checker ON paint_checking.id_checker = checker.id\n" +
                    "JOIN paint ON paint_checking.id_paint = paint.id\n" +
                    "JOIN artists ON paint.id_artist = artists.id\n" +
                    "JOIN gallery ON paint_checking.id_gal = gallery.id;" );
//                    "WHERE date_part('year', check_date)>date_part('year', current_date)-5;");
            ResultSet rs = preparedStatement.executeQuery();
            List<MessagePart> entities = new LinkedList<>();
            while(rs.next()){
                MessagePart entity = new MessagePart(LocalDate.parse(rs.getObject("check_date").toString()),
                        rs.getString("checker_name"), rs.getBoolean("need_rest"), rs.getInt("id"), rs.getString("paint_name"), rs.getString("fio"),
                        rs.getString("gallery_name"));
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createGallery(NotNormEntity entity){
        try(Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO gallery(id, name, location) VALUES(?, ? , ?);");
            statement.setInt(1, entity.getGal_id());
            statement.setString(2, entity.getGal_name());
            statement.setString(3, entity.getGal_location());
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("повтор");
        }
    }
	public void createArtist(NotNormEntity entity) {
        try(Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO artists(id, fio, b_city, have_awards) VALUES(?, ? , ?, ?);");
            statement.setInt(1, entity.getArtist_id());
            statement.setString(2, entity.getArtist_fio());
            statement.setString(3, entity.getArtist_b_city());
			statement.setBoolean(4, entity.isArtist_have_awards());
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("повтор");
        }
    }
	public void createChecker(NotNormEntity entity){
        try(Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO checker(id, name, start_year) VALUES(?, ? , ?);");
            statement.setInt(1, entity.getChecker_id());
            statement.setString(2, entity.getChecker_name());
            statement.setObject(3, entity.getStart_year(), Types.DATE);
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("повтор");
        }
    }
	public void createPaintChecking(NotNormEntity entity){
        try(Connection conn = dataSource.getConnection()){
            PreparedStatement statement = conn.prepareStatement("INSERT INTO paint_checking(check_date, need_rest, marks, id_checker, id_gal, id_paint, id) VALUES(?, ? , ?, ?, ?, ?, ?);");
            statement.setObject(1, entity.getCheck_date(), Types.DATE);
            statement.setBoolean(2, entity.isNeed_rest());
			statement.setString(3, entity.getMarks());
			statement.setInt(4, entity.getChecker_id());
			statement.setInt(5, entity.getGal_id());
			statement.setInt(6, entity.getId_paint());
			statement.setInt(7, entity.getId_check_date());
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("повтор");
        }
    }
    public void createPaint(NotNormEntity entity) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO paint(id, name, price, id_artist) VALUES(?, ?, ?, ?);");
            statement.setInt(1, entity.getId_paint());
            statement.setString(2, entity.getPaint_name());
            statement.setInt(3, entity.getPaint_price());
            statement.setInt(4, entity.getArtist_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("повтор");
        }
    }
	
}
