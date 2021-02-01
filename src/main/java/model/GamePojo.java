package model;

import observer.Observer;

import java.time.LocalDateTime;

public class GamePojo {

	private int userId;
	private int boardId;
	private LocalDateTime startTime;
	private LocalDateTime finishTime;

	public GamePojo(int userId, int boardId) {
		this.userId = userId;
		this.boardId = boardId;
	}

	public GamePojo(int userId,
					int boardId,
					LocalDateTime startTime) {
		this.userId = userId;
		this.boardId = boardId;
		this.startTime = startTime;
	}

	public GamePojo(int userId,
					int boardId,
					LocalDateTime startTime,
					LocalDateTime finishTime) {
		this.userId = userId;
		this.boardId = boardId;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}


	public int getUserId() {
		return userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public String toString() {
		return "GamePojo{" +
				"userId=" + userId +
				", boardId=" + boardId +
				", startTime=" + startTime +
				", finishTime=" + finishTime +
				'}';
	}
}
