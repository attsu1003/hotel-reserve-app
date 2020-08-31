package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.command.DeleteCommand;
import com.example.demo.application.command.ReserveCommand;
import com.example.demo.application.command.UpdateReserveCommand;
import com.example.demo.domain.reserve.Amount;
import com.example.demo.domain.reserve.DeleteFailedException;
import com.example.demo.domain.reserve.NoVacancyRoomException;
import com.example.demo.domain.reserve.Reserve;
import com.example.demo.domain.reserve.ReserveId;
import com.example.demo.domain.reserve.ReserveRepository;
import com.example.demo.domain.reserve.ReserveService;
import com.example.demo.domain.reserve.TotalHotelFee;
import com.example.demo.domain.reserve.UpdateFailedException;
import com.example.demo.domain.room.RoomRepository;

@Component
public class ReserveApplicationServiceImpl implements ReserveApplicationService {

	@Autowired
	private ReserveRepository reserveRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ReserveService reserveService;

	@Override
	public void execute(ReserveCommand reserveCommand) throws NoVacancyRoomException {
		if (!reserveService.isReservable(reserveCommand.getCheckInDay(), reserveCommand.getCheckOutDay(),
				roomRepository.countRoom())) {
			throw new NoVacancyRoomException("空き部屋がありません。別のチェックイン日、チェックアウト日を入力してください。");
		}
		Reserve reserve = new Reserve(reserveCommand.getPlan(), reserveCommand.getCheckInDay(),
				reserveCommand.getCheckOutDay(), reserveCommand.getNumberOfGuest(),
				new TotalHotelFee(new Amount(reserveCommand.getTotalHotelFee())), reserveCommand.getMemberId());
		reserveRepository.reserve(reserve);
	}

	@Override
	public void execute(UpdateReserveCommand updateReserveCommand) throws NoVacancyRoomException, UpdateFailedException {
		if (!reserveService.isReservable(updateReserveCommand.getCheckInDay(), updateReserveCommand.getCheckOutDay(),
				roomRepository.countRoom())) {
			throw new NoVacancyRoomException("空き部屋がありません。別のチェックイン日、チェックアウト日を入力してください。");
		}
		// todo reserveエンティティを変化させるように修正
		Reserve reserve = new Reserve(new ReserveId(updateReserveCommand.getReserveId()),
				updateReserveCommand.getPlan(), updateReserveCommand.getCheckInDay(),
				updateReserveCommand.getCheckOutDay(), updateReserveCommand.getNumberOfGuest(),
				new TotalHotelFee(new Amount(updateReserveCommand.getTotalHotelFee())),
				updateReserveCommand.getMemberId());
		if(!reserveRepository.updateReserve(reserve)) {
			 throw new UpdateFailedException("選択した予約の更新に失敗しました。恐れ入りますが管理者にお問い合わせください。");
		}
	}

	@Override
	public void execute(DeleteCommand deleteCommand) throws DeleteFailedException {
		if(!reserveRepository.deleteReserve(deleteCommand.getReserveId())) {
			throw new DeleteFailedException("選択した予約の削除に失敗しました。恐れ入りますが管理者にお問い合わせください。");
		}
	}

}
