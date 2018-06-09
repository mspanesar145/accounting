package com.accounting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.accounting.UserDocument;

@Service
public class AccountingReportDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String,String>> getAllCities(){

		String sql = "select name from city";

		System.out.println("city sql : "+sql);

		return jdbcTemplate.query(sql, new RowMapper<Map<String,String>>() {
			@Override
			public Map<String,String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String,String> map = new LinkedHashMap<>();
				map.put("name", rs.getString("name"));
				return map;
			}

		});
	}
}
