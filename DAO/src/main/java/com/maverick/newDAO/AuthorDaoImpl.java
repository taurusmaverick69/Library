package com.maverick.newDAO;

import com.maverick.domain.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class AuthorDaoImpl extends AbstractDao implements AuthorDao {

    private static final RowMapper AUTHOR_MAPPER = (rs, i) -> {
        Author author = new Author();
        author.setId(rs.getInt("id"));
        author.setFullName(rs.getString("fullName"));
        author.setYearsOfLife(rs.getString("yearsOfLife"));
        return author;
    };

    public Author getById(int id) {
        return (Author) jdbcTemplate.queryForObject("SELECT * FROM booksdb.author WHERE id=?", new Object[]{id}, AUTHOR_MAPPER);
    }

    public List<Author> getAll() {
        return jdbcTemplate.query("SELECT * FROM booksdb.author", AUTHOR_MAPPER);
    }

    public long insert(Author author) {

        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO booksdb.author (full_name, years_of_life) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getFullName());
            ps.setString(2, author.getYearsOfLife());
            return ps;
        }, key);
        return key.getKey().intValue();
    }

    public long update(Author author) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("UPDATE booksdb.author SET full_name=?, years_of_life=? WHERE id=?");
            ps.setString(1, author.getFullName());
            ps.setString(2, author.getYearsOfLife());
            ps.setLong(3, author.getId());
            return ps;
        });
        return author.getId();
    }

    public void delete(int id) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("DELETE FROM booksdb.author WHERE id=?");
            ps.setInt(1, id);
            return ps;
        });
    }

}
