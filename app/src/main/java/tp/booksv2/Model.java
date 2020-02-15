package tp.booksv2;


public class Model {
    private int id;
    private String TITULO;
    private String AUTOR;
    private String SINOPSIS;
    private String FECHA_DE_PUBLICACION;
    private String EDITORIAL;
    private String CANTIDAD_DE_PAGINAS;
    private String IDIOMA;
    private String CATEGORIA;




    public Model(int id, String TITULO,
                 String AUTOR,
                 String SINOPSIS,
                 String FECHA_DE_PUBLICACION,
                 String EDITORIAL,
                 String CANTIDAD_DE_PAGINAS,
                 String IDIOMA,
                 String CATEGORIA) {
        this.id = id;
        this.TITULO = TITULO;
        this.AUTOR = AUTOR;
        this.SINOPSIS = SINOPSIS;
        this.FECHA_DE_PUBLICACION = FECHA_DE_PUBLICACION;
        this.EDITORIAL = EDITORIAL;
        this.CANTIDAD_DE_PAGINAS = CANTIDAD_DE_PAGINAS;
        this.IDIOMA = IDIOMA;
        this.CATEGORIA = CATEGORIA;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAUTOR() {
        return AUTOR;
    }

    public void setAUTOR(String AUTOR) {
        this.AUTOR = AUTOR;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getSINOPSIS() {
        return SINOPSIS;
    }

    public void setSINOPSIS(String SINOPSIS) {
        this.SINOPSIS = SINOPSIS;
    }





    public String getFECHA_DE_PUBLICACION() {
        return FECHA_DE_PUBLICACION;
    }

    public void setFECHA_DE_PUBLICACION(String FECHA_DE_PUBLICACION) {
        this.FECHA_DE_PUBLICACION = FECHA_DE_PUBLICACION;
    }



    public String getEDITORIAL() {
        return EDITORIAL;
    }

    public void setEDITORIAL(String EDITORIAL) {
        this.EDITORIAL = EDITORIAL;
    }


    public String getCANTIDAD_DE_PAGINAS() {
        return CANTIDAD_DE_PAGINAS;
    }

    public void setCANTIDAD_DE_PAGINAS(String CANTIDAD_DE_PAGINAS) {
        this.CANTIDAD_DE_PAGINAS = CANTIDAD_DE_PAGINAS;
    }


    public String getIDIOMA() {
        return IDIOMA;
    }

    public void setIDIOMA(String IDIOMA) {
        this.IDIOMA = IDIOMA;
    }

    public String getCATEGORIA() {
        return CATEGORIA;
    }

    public void setCATEGORIA(String CATEGORIA) {
        this.CATEGORIA = CATEGORIA;
    }







}
