package org.zerock.domain.caregiver.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.betSeniorCare.repository.BetRepo;
import org.zerock.domain.caregiver.dto.request.CaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.request.UpdateInfoCaregiverRequestDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverQueResponseDTO;
import org.zerock.domain.caregiver.dto.response.CaregiverResponseDTO;
import org.zerock.domain.caregiver.entity.Caregiver;
import org.zerock.domain.caregiver.repository.CaregiverRepo;
import org.zerock.global.aws.S3Service;

import lombok.extern.java.Log;

@Service
@Log
public class CaregiverServiceImpl implements CaregiverService{
	
	@Autowired
	private CaregiverRepo caregiverRepo;
	
	@Autowired
	private BetRepo betRepo;
	
	@Autowired
	private S3Service s3Service;

	@Override
	public void createCaregiver(CaregiverRequestDTO dto, MultipartFile profile, MultipartFile certiImage) throws Exception {
		// 이미 있는 아이디인지 검증
		if(caregiverRepo.existsByCid(dto.getCid())) { // senior에서도 확인 필요
			throw new EntityExistsException();
		}
		
		Caregiver saveCaregiver = dto.toCaregiverEntity(dto);
		
		String profileUrl = s3Service.uploadFile(profile, "profile"); // 앞은 파일, 뒤는 aws의 디렉토리 경로
		String certiUrl = s3Service.uploadFile(certiImage, "certificate"); // 앞은 파일, 뒤는 aws의 디렉토리 경로
		
		// 이미지 경로 db에 저장
		saveCaregiver.setProfile(profileUrl);
		saveCaregiver.setCertilmage(certiUrl);
		
		caregiverRepo.save(saveCaregiver);
		
	}

	@Override
	public void updateInfoCaregiver(UpdateInfoCaregiverRequestDTO dto, long cid) throws Exception {
		Optional<Caregiver> updateCaregiver = caregiverRepo.findById(cid);
		
		updateCaregiver.ifPresent(cg -> {
			cg.setChar1(dto.getChar1());
			cg.setChar2(dto.getChar2());
			cg.setChar3(dto.getChar3());
			cg.setWorkday(Integer.parseInt(dto.getWorkday()));
			cg.setInfo(dto.getInfo());
			cg.setVisitTime(dto.getVisittime());
			cg.setExp(dto.getExp());
			cg.setCertifi(dto.getCertifi());
			cg.setGood(dto.getGood());
			cg.setGoal(dto.getGoal());
			
			caregiverRepo.save(cg);	
		});
	}

	@Override
	public CaregiverResponseDTO getCaregiverById(long careno) throws Exception {
		Caregiver caregiver = caregiverRepo.findById(careno).get();
		CaregiverResponseDTO dto = caregiver.toCaregiverResponseDTO(caregiver);
		
		// 성별 변환 
		if(caregiver.getGender() == 0) { // 0: 남성, 1: 여성
			dto.setGenderStr("남성");		
		} else {
			dto.setGenderStr("여성");		
		}
		
		// 년도 -> 나이로 변환 (만 나이를 기준으로 합니다)
		LocalDate now = LocalDate.now();	
		int year = now.getYear(); // 올해 년도
		String birth = caregiver.getBirth(); // ex) 19981119 형태
		int birthYear = Integer.parseInt(birth.substring(0,4)); // 1998만 뽑아옴
		
		dto.setAge(year - birthYear +1);
		dto.setProfile(caregiver.getProfile());
		return dto;
		
		
	}

	
	@Override
	public CaregiverQueResponseDTO getCaregiverAns(long careno, int queno) throws Exception {
		Caregiver caregiver = caregiverRepo.findById(careno).get();
		CaregiverQueResponseDTO dto = new CaregiverQueResponseDTO(); // 초기화
		dto.setName(caregiver.getName());
		
		// 질문에 따른 답변을 가져옴
		if(queno == 1) { // 자기소개
			dto.setAnswer(caregiver.getInfo());
		} else if(queno == 2) { // 방문 일정
			dto.setAnswer(caregiver.getVisitTime());
		} else if (queno == 3) { // 경험
			dto.setAnswer(caregiver.getExp());
		} else if (queno == 4) { // 자격증
			dto.setAnswer(caregiver.getCertifi());
		} else if (queno == 5) { // 장점
			dto.setAnswer(caregiver.getGood());
		} else if (queno == 6) { // 포부
			dto.setAnswer(caregiver.getGoal());
		}
	
		return dto;	
	}

	@Override
	public List<CaregiverResponseDTO> getCaregiverList(int year, int month, BigDecimal lon, BigDecimal lat) throws Exception {
		
		return null;
	}
	
    

	//두 좌표 간의 거리 계산 메소드
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
     
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
	
    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
         
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;
        
        return (dist);
    }


}
