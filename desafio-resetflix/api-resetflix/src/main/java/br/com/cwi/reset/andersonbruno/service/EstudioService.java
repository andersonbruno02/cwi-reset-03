package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.domain.Estudio;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.EstudioRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EstudioService {

    private Integer id = 0;
    private FakeDatabase fakeDatabase;

    public EstudioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarEstudio(EstudioRequest estudioRequest) throws customExceptions {

        if (estudioRequest.getNome().isEmpty() || estudioRequest.getNome().equals("")) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo nome");
        }
        if (estudioRequest.getDescricao().isEmpty() || estudioRequest.getDescricao().equals("")) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo descricao");
        }
        if (estudioRequest.getDataCriacao() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo dataCriacao");
        }
        if (estudioRequest.getStatusAtividade() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo statusAtividade");
        }

        if (estudioRequest.getDataCriacao().isAfter(LocalDate.now())) {
            throw new customExceptions("Não é possível cadastrar estúdios do futuro.");
        }

        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        String nomeIgual = estudioRequest.getNome();
        for (Estudio estudio : estudios) {
            if (estudio.getNome().equals(nomeIgual)) {
                throw new customExceptions("Já existe um estúdio cadastrado para o nome " + nomeIgual);
            }
        }
        this.id++;
        Estudio estudio = new Estudio(this.id, estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        fakeDatabase.persisteEstudio(estudio);
    }

    public List<Estudio> consultarEstudios(String filtroNome) throws customExceptions {
        final List<Estudio> estudios = fakeDatabase.recuperaEstudios();

        if (estudios.isEmpty()) {
            throw new customExceptions("Nenhum estúdio cadastrado, favor cadastar estúdios.");
        }
        final List<Estudio> estudioFiltroNome = new ArrayList<>();
        if (filtroNome != null) {
            for (Estudio estudio : estudios) {
                if (estudio.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT))) {
                    estudioFiltroNome.add(new Estudio(estudio.getId(), estudio.getNome(), estudio.getDescricao(), estudio.getDataCriacao(), estudio.getStatusAtividade()));
                }
            }
        } else {
            for (Estudio estudio : estudios) {
                estudioFiltroNome.add(new Estudio(estudio.getId(), estudio.getNome(), estudio.getDescricao(), estudio.getDataCriacao(), estudio.getStatusAtividade()));
            }
        }
        if (estudioFiltroNome.isEmpty()) {
            throw new customExceptions("Estúdio não encontrado com o filtro " + filtroNome + ", favor informar outro filtro.");
        }
        return estudioFiltroNome;
    }

    public Estudio consultarEstudio(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        if (id > estudios.size() || id <= 0) {
            throw new customExceptions("Nenhum estúdio encontrado com o parâmetro id=" + id + ", favor verifique os parâmetros informados.");
        }
        Estudio estudioId = null;
        for (Estudio estudio : estudios) {
            if(estudio.getId().equals(id)) {
                estudioId = estudio;
            }
        }
        return estudioId;
    }
}
