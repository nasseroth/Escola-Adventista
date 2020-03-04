import { API_CONFIG } from 'src/config/api.config';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ClasseDto } from '../dto/classe-dto';
import { Observable } from 'rxjs';
import { take, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClasseService {

  constructor(private http: HttpClient) { }

  post(classe: ClasseDto): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/classe/cadastrar`, classe).pipe(take(1));
  }

  put(classe: ClasseDto): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/classe/${classe.id}`, classe).pipe(take(1));
  }
  findAll(): Observable<Array<ClasseDto>> {
    return this.http.get<Array<ClasseDto>>(`${API_CONFIG.baseUrl}/api/classe/mostrar-tudo`).pipe(take(1));
  }

  totalClasses(): Observable<any> {
    return this.http.get<any>(`${API_CONFIG.baseUrl}/api/classe/total-classes`).pipe(take(1));
  }

  findById(id: number): Observable<ClasseDto> {
    return this.http.get<ClasseDto>(`${API_CONFIG.baseUrl}/api/classe/${id}`).pipe(take(1));
  }

  delete(id: number): Observable<any> {
    return this.http.delete<ClasseDto>(`${API_CONFIG.baseUrl}/api/classe/${id}`).pipe(take(1));
  }
}
