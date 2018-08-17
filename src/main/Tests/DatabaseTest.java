package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Server.Connections.Database;

class DatabaseTest {

	Database database;

	@BeforeEach
	void setUp() {
		database = new Database();
		database.setupDB();
	}

	@Test
	public void getIdTest() {
		assertEquals(6, Database.getMaxID("Chat", "ChatID"), "Not Match");
	}

}