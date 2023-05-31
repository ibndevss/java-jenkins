package myor.matrix.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myor.matrix.master.entity.SearchBrowseDto;
import myor.matrix.master.repository.ChannelRepository;
import myor.matrix.master.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepository repoChannel;
	
	@Override
	public List<SearchBrowseDto> getBrowseChannel() {
		// TODO Auto-generated method stub
		return repoChannel.getBrowseChannel();
	}

}
