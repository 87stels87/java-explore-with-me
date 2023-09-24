package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatsDto;
import ru.practicum.exception.StatisticsValidationException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.Util.FORMATTER;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HitServiceImpl implements HitService {

    private final HitRepository hitRepository;

    @Transactional
    @Override
    public void addHit(HitDto hitDto) {

        hitRepository.save(HitMapper.returnHit(hitDto));
    }

    @Override
    public List<StatsDto> getStats(String start, String end, List<String> uris, Boolean unique) {

        LocalDateTime startTime = LocalDateTime.parse(start, FORMATTER);
        LocalDateTime endTime = LocalDateTime.parse(end, FORMATTER);


        if (startTime != null && endTime != null) {
            if (startTime.isAfter(endTime)) {
                throw new StatisticsValidationException("Start must be after End");
            }
        }

        if (uris == null || uris.isEmpty()) {
            if (unique) {
                return hitRepository.findAllStatsByUniqIp(startTime, endTime);
            } else {
                return hitRepository.findAllStats(startTime, endTime);
            }
        } else {
            if (unique) {
                return hitRepository.findStatsByUrisByUniqIp(startTime, endTime, uris);
            } else {
                return hitRepository.findStatsByUris(startTime, endTime, uris);
            }
        }
    }
}