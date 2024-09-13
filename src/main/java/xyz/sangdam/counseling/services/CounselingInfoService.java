package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.CounselingSearch;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.exceptions.CounselingNotFoundException;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.file.entities.FileInfo;
import xyz.sangdam.file.services.FileInfoService;
import xyz.sangdam.global.ListData;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselingInfoService {

    private final CounselingRepository repository;
    private final FileInfoService fileInfoService;

    public Counseling get(Long cNo) { // 한 개 조회
        Counseling item = repository.findById(cNo).orElseThrow(CounselingNotFoundException::new);

        // 추가 처리
        addInfo(item);

        return item;
    }

    public ListData<Counseling> getList(CounselingSearch search) { // 목록 조회
        return null;
    }

    private void addInfo(Counseling item) { // 추가 정보
        try { // 이미지 추가 기능
            List<FileInfo> editorImages = fileInfoService.getList(item.getGid(), "editor");
            item.setEditorImages(editorImages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}