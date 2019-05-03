package nl.vpro.amara.domain;

import lombok.Data;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author joost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Language {
    private String code;
    private String name;
    private String ltr;


    @Override
    public String toString() {
        return "AmaraLanguage{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", ltr='" + ltr + '\'' +
                '}';
    }

    public Locale toLocale() {
        return new Locale(code);
    }

}
