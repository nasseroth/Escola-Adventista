import { AlunoDto } from './../dto/aluno-dto';
import { API_CONFIG } from 'src/config/api.config';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {

  constructor(private http: HttpClient) { }

  post(aluno: AlunoDto): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/aluno/cadastrar`, aluno).pipe(take(1));
  }

  put(aluno: AlunoDto): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/aluno/${aluno.id}`, aluno).pipe(take(1));
  }

  totalAlunos(): Observable<any> {
    return this.http.get<any>(`${API_CONFIG.baseUrl}/api/aluno/total-alunos`).pipe(take(1));
  }


  findAll(): Observable<Array<AlunoDto>> {
    return this.http.get<Array<AlunoDto>>(`${API_CONFIG.baseUrl}/api/aluno/mostrar-tudo`).pipe(take(1));
  }

  findById(id: number): Observable<AlunoDto> {
    return this.http.get<AlunoDto>(`${API_CONFIG.baseUrl}/api/aluno/${id}`).pipe(take(1));
  }

  delete(id: number): Observable<any> {
    return this.http.delete<AlunoDto>(`${API_CONFIG.baseUrl}/api/aluno/${id}`).pipe(take(1));
  }
}
