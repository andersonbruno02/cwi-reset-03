package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.domain.Diretor;

import br.com.cwi.reset.andersonbruno.repository.DiretorRepositoryBd;
import br.com.cwi.reset.andersonbruno.request.DiretorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepositoryBd diretorRepositoryBd;

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws customExceptions {
        if (!diretorRequest.getNome().contains(" ")) {
            throw new customExceptions("Deve ser informado no mínimo nome e sobrenome para o diretor.");
        }

        if (diretorRequest.getDataNascimento().getYear() > diretorRequest.getAnoInicioAtividade()) {
            throw new customExceptions("Ano de início de atividade inválido para o diretor cadastrado.");
        }

        Diretor diretorExistente = diretorRepositoryBd.findByNome(diretorRequest.getNome());

        if (diretorExistente != null) {
            throw new customExceptions("Já existe um diretor cadastrado para o nome " + diretorRequest.getNome());
        }


        Diretor diretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        diretorRepositoryBd.save(diretor);
    }

    public List<Diretor> listarDiretores(String filtroNome) throws customExceptions {
        List<Diretor> diretores = diretorRepositoryBd.findAll();
        if (diretores.isEmpty()) {
            throw new customExceptions("Nenhum diretor cadastrado, favor cadastar diretores.");
        }
        if (filtroNome == null) {
            return diretores;
        } else {
            List<Diretor> diretorFiltoNome = diretorRepositoryBd.findByNomeContains(filtroNome);
            if (diretorFiltoNome.isEmpty()) {
                throw new customExceptions("Diretor não encontrato com o filtro " + filtroNome + ", favor informar outro filtro");
            }

            return diretorFiltoNome;
        }
    }

    public Diretor consultarDiretor(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Id");
        }
        List<Diretor> diretores = diretorRepositoryBd.findAll();
        if (id > diretores.size() || id <= 0) {
            throw new customExceptions("Nenhum diretor encontrado com o parâmetro id= " + id + ", favor verifique os parâmetros informados.");
        }

        Diretor diretorId = diretorRepositoryBd.findById(id).get();
        return diretorId;
    }

    public void atualizarDiretor(Integer id, DiretorRequest diretorRequest) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        boolean verificaDiretor = diretorRepositoryBd.existsById(id);
        if (!verificaDiretor) {
            throw new customExceptions("Nenhum diretor encontrado com o parâmetro id " + id + " favor verifique os parâmetros informados");
        }
        Diretor novoDiretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        novoDiretor.setId(id);
        diretorRepositoryBd.save(novoDiretor);
    }
    public void removerDiretores(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        Optional<Diretor> diretor = diretorRepositoryBd.findById(id);
        Diretor diretorDeletado = diretor.get();
        diretorRepositoryBd.delete(diretorDeletado);

    }
}
