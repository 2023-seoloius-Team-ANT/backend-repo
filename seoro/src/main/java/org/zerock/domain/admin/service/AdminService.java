package org.zerock.domain.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.caregiver.entity.Caregiver;


@Service
public interface AdminService {

	public List<Caregiver> getCaregiver(Integer regCheck) throws Exception;
}
