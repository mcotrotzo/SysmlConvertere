package org.example.Mapping.Interfaces;


import java.util.List;

public interface Block extends Action {
	List<Action> getActions();

	List<Succession> getSuccessions();
}
