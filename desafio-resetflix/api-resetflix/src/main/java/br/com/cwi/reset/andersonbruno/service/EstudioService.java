package br.com.cwi.reset.andersonbruno.service;


import br.com.cwi.reset.andersonbruno.domain.Estudio;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.repository.EstudioRepositoryBd;
import br.com.cwi.reset.andersonbruno.request.EstudioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepositoryBd estudioRepositoryBd;

    public void criarEstudio(EstudioRequest estudioRequest) throws customExceptions {

        Estudio estudioExistente = estudioRepositoryBd.findByNome(estudioRequest.getNome());
        if (estudioExistente != null) {
            throw new customExceptions("Já existe um estúdio cadastrado para o nome " + estudioRequest.getNome());
        }
        Estudio estudio = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        estudioRepositoryBd.save(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws customExceptions {
        List<Estudio> estudios = estudioRepositoryBd.findAll();
        if (estudios.isEmpty()) {
            throw new customExceptions("Nenhum estúdio cadastrado, favor cadastar estúdios.");
        }
        if (filtroNome == null) {
            return estudioRepositoryBd.findAll();
        } else {
            List<Estudio> estudioFiltroNome = estudioRepositoryBd.findByNomeContains(filtroNome);

            if (estudioFiltroNome.isEmpty()) {
                throw new customExceptions("Estúdio não encontrado com o filtro " + filtroNome + ", favor informar outro filtro.");
            }
            return estudioFiltroNome;
        }
    }

    public Estudio consultarEstudio(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        List<Estudio> estudios = estudioRepositoryBd.findAll();
        if (id > estudios.size() || id <= 0) {
            throw new customExceptions("Nenhum estúdio encontrado com o parâmetro id=" + id + ", favor verifique os parâmetros informados.");
        }
        Estudio estudioId = estudioRepositoryBd.findById(id).get();
        return estudioId;
    }
}
