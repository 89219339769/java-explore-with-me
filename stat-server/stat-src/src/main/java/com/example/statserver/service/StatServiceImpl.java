package com.example.statserver.service;

import com.example.statserver.model.EndpointHit;
import com.example.statserver.model.ViewStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    @Override
    public EndpointHit saveStat(EndpointHit endpointHit) {

        return    statRepository.save(endpointHit);

    }

    @Override
    public List<ViewStats> getStat(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (unique) {
            return getUniqIpStat(startDate, endDate, uris);

        } else {
            return getAllIpStat(startDate, endDate, uris);
        }
    }

    private List<ViewStats> getUniqIpStat(LocalDateTime startDate, LocalDateTime endDate, List<String> uris) {
        List<EndpointHit> endpointHitsUniq = statRepository.getStat(startDate, endDate);
        List<ViewStats> viewStats = new ArrayList<>();

        for (int i = 0; i < endpointHitsUniq.size(); i++) {
            String uri = endpointHitsUniq.get(i).getUri();
            List<EndpointHit> viewStatsFiltred = new ArrayList<>();

            for (int j = 0; j < endpointHitsUniq.size(); j++) {
                if (endpointHitsUniq.get(j).getUri().equals(uri))
                    viewStatsFiltred.add(endpointHitsUniq.get(j));
            }
            List<String> hitsIp = new ArrayList<>();

            List<String> listWithoutDuplicates = new ArrayList<>(
                    new HashSet<>(hitsIp));


            for (int j = 0; j < viewStatsFiltred.size(); j++) {
                listWithoutDuplicates.add(viewStatsFiltred.get(j).getIp());
            }

            int hitCount = listWithoutDuplicates.size();

            viewStats.add(new ViewStats(endpointHitsUniq.get(i).getApp(), endpointHitsUniq.get(i).getUri(), hitCount));
        }
        return sortAndFind(viewStats, uris);
    }

    private List<ViewStats> getAllIpStat(LocalDateTime startDate, LocalDateTime endDate, List<String> uris) {
        List<EndpointHit> endpointHits = statRepository.getStat(startDate, endDate);
        List<ViewStats> viewStats = new ArrayList<>();

        for (int i = 0; i < endpointHits.size(); i++) {
            String uri = endpointHits.get(i).getUri();
            List<EndpointHit> viewStatsF = new ArrayList<>();

            for (int j = 0; j < endpointHits.size(); j++) {
                if (endpointHits.get(j).getUri().equals(uri))
                    viewStatsF.add(endpointHits.get(j));
            }
            int hitCount = viewStatsF.size();

            viewStats.add(new ViewStats(endpointHits.get(i).getApp(), endpointHits.get(i).getUri(), hitCount));
        }
        return sortAndFind(viewStats, uris);
    }

    private List<ViewStats> sortAndFind(List<ViewStats> viewStatsSort, List<String> uris) {
        List<ViewStats> viewStatsSortFiltred = new ArrayList<>();
        List<ViewStats> viewStatsSortFiltredUri = new ArrayList<>();
        viewStatsSortFiltred = viewStatsSort.stream()
                .sorted((o1, o2) -> {
                    int result = o1.getHits().compareTo(o2.getHits());
                    return result * -1;
                })
                .collect(Collectors.toList());

        for (ViewStats viewStat :  viewStatsSortFiltred) {
            if (uris.contains(viewStat.getUri()))
                viewStatsSortFiltredUri.add(viewStat);
        }

        return viewStatsSortFiltredUri.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}




