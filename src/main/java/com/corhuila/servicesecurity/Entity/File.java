package com.corhuila.servicesecurity.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="file", schema = "parameter")
public class File extends BaseModel{

	@Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "file_extension", nullable = false, length = 20)
    private String fileExtension;

    @Column(name = "table_name", nullable = false, length = 20)
    private String table;
    
    @Column(name = "table_id", nullable = false, length = 20)
    private String tableId;
    
    @Column(name = "archive", nullable = false, columnDefinition = "TEXT")
    private String archive;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}
    
}
