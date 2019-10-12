package com.wooboo.dsp.base.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author duanwei
 *
 */
public enum StatusEnums {
	
	UNSET(-1L,"未定义"),
	PENDING(1L,"待审核"),
	PROCESS(2L,"审核中"),
	PASS(3L,"审核完成"),
	REFUSE(4L,"审核拒绝"),
	CLOSED(5L,"紧急下线"),
	
	
	ING(8L,"待审核&审核中"),
	;
	
	private static Map<Long, StatusEnums> CodeMap;

	static {
		CodeMap = new HashMap<Long, StatusEnums>();
		StatusEnums[] values = StatusEnums.values();
		for (StatusEnums value : values) {
			CodeMap.put(value.getId(), value);
		}
	}
	
	private Long id;
	private String name;

	private StatusEnums(Long id,String name){
		this.id = id;
		this.name = name;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	public static StatusEnums fromId(Long id) {
		if (CodeMap.containsKey(id)) {
			return CodeMap.get(id);
		}

		return UNSET;
	}

	
	
}
