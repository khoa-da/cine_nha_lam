package com.fap.cinanhalam.service.impl;

import com.fap.cinanhalam.Enum.FilmStatus;
import com.fap.cinanhalam.converter.GenericConverter;
import com.fap.cinanhalam.dto.FilmDTO;
import com.fap.cinanhalam.entity.CategoryEntity;
import com.fap.cinanhalam.entity.FilmEntity;
import com.fap.cinanhalam.repository.CategoryRepository;
import com.fap.cinanhalam.repository.FilmRepository;
import com.fap.cinanhalam.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FilmService implements IGenericService<FilmDTO> {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    GenericConverter genericConverter;

    @Override
    public List<FilmDTO> findAll() {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findAll();
        for (FilmEntity entity: entities){
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }

    @Override
    public List<FilmDTO> findAllWithStatusIsTrue() {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findAllByStatusTrue();
        for(FilmEntity entity : entities){
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }

    @Override
    public FilmDTO save(FilmDTO filmDTO) {
        FilmEntity filmEntity;
        if (filmDTO.getId() != null) {
            FilmEntity oldEntity = filmRepository.findOneById(filmDTO.getId());
            filmEntity = (FilmEntity) genericConverter.updateEntity(filmDTO, oldEntity);
        } else {
            String convertId = getTrailerVideoId(filmDTO.getTrailerUrl());
            filmDTO.setTrailerUrl(convertId);
            if (filmDTO.getReleaseDate() != null) {
                Date currentDate = new Date();
                Date releaseDate = filmDTO.getReleaseDate();

                if (releaseDate.before(currentDate)) {
                    filmDTO.setFilmStatus(FilmStatus.Showing.toString());
                } else {
                    filmDTO.setFilmStatus(FilmStatus.Coming.toString());
                }
            }
            filmEntity = (FilmEntity) genericConverter.toEntity(filmDTO, FilmEntity.class);
        }
        filmRepository.save(filmEntity);
        return (FilmDTO) genericConverter.toDTO(filmEntity, FilmDTO.class);
    }

    @Override
    public void changeStatus(Long ids) {
        FilmEntity filmEntity = filmRepository.findOneById(ids);
        if (filmEntity.getStatus() == true) {
            filmEntity.setStatus(false);
        } else {
            filmEntity.setStatus(true);
        }
        filmRepository.save(filmEntity);
    }

    @Override
    public int totalItem() {
        return (int)filmRepository.count();
    }

    @Override
    public List<FilmDTO> findAll(Pageable pageable) {
        return null;
    }

    // In ra thể loại của bộ phim đó
    public List<FilmDTO> findOneWithStatusIsTrue(Long id) {
        List<FilmDTO> result = new ArrayList<>();
        FilmEntity entity = filmRepository.findOneByIdAndStatusTrue(id);
        FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
        result.add(filmDTO);
        return result;
    }

    // Lấy ID trailer của youtube
    public String getTrailerVideoId(String trailerUrl) {
        if (trailerUrl != null && !trailerUrl.isEmpty()) {
            String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed\\?video_id=|youtu.be%2F|\\/v%2F|watch\\?v=|\\/e\\/|v\\/|watch\\?v=|youtu.be\\/|embed\\/|watch\\?feature=player_embedded&v=|embed\\?video_id=|youtu.be%2F|\\/v%2F)([^\"&?\\/\\s]{11})";
            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(trailerUrl);
            if (matcher.find()) {
                return matcher.group();
            }
        }
        return null;
    }

    public List<FilmDTO> findTop4FilmBaseOnOrderDetail(){
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findTop4FilmsWithMostTicketDetails();
        for(FilmEntity entity : entities){
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }

    // Lấy ra các bộ phim đang chiếu
    public List<FilmDTO> findAllFilmShowing() {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findAllFilmShowing();
        for (FilmEntity entity : entities) {
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }

    // Lấy ra các bộ phim sắp chiếu
    public List<FilmDTO> findAllFilmComing() {
        List<FilmDTO> result = new ArrayList<>();
        List<FilmEntity> entities = filmRepository.findAllFilmComing();
        for (FilmEntity entity : entities) {
            FilmDTO filmDTO = (FilmDTO) genericConverter.toDTO(entity, FilmDTO.class);
            result.add(filmDTO);
        }
        return result;
    }
}