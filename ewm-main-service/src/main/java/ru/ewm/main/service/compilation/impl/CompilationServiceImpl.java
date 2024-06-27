package ru.ewm.main.service.compilation.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.Compilation;
import ru.ewm.main.repository.CompilationRepository;
import ru.ewm.main.service.compilation.CompilationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;

    @Override
    @Transactional
    public Compilation save(Compilation compilation) {
        return compilationRepository.save(compilation);
    }

    @Override
    public Compilation getByIdOrThrow(long id) {
        return compilationRepository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.getCompilationNotFoundException(id));
    }

    @Override
    public void deleteById(long id) {
        if (!compilationRepository.existsById(id)) {
            throw ExceptionUtil.getCompilationNotFoundException(id);
        }
        compilationRepository.deleteById(id);
    }

    @Override
    public Page<Compilation> findAllByPinned(Boolean pinned, Pageable pageable) {
        return compilationRepository.findAllByPinned(pinned, pageable);
    }

}
