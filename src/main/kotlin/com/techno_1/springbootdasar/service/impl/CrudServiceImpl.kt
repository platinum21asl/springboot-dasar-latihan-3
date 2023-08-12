package com.techno_1.springbootdasar.service.impl

import com.techno_1.springbootdasar.domain.dto.request.ReqMahasiswaDto
import com.techno_1.springbootdasar.domain.dto.request.ReqProdiDto
import com.techno_1.springbootdasar.domain.dto.request.ReqUserDto
import com.techno_1.springbootdasar.domain.dto.response.ResBaseDto
import com.techno_1.springbootdasar.domain.dto.response.ResMahasiswaDto
import com.techno_1.springbootdasar.domain.dto.response.ResProdiDto
import com.techno_1.springbootdasar.domain.dto.response.ResUserDto
import com.techno_1.springbootdasar.domain.entity.MahasiswaEntity
import com.techno_1.springbootdasar.domain.entity.ProdiEntity
import com.techno_1.springbootdasar.domain.entity.UserEntity
import com.techno_1.springbootdasar.exception.CustomExceptionHandler
import com.techno_1.springbootdasar.repository.MahasiswaRepository
import com.techno_1.springbootdasar.repository.ProdiRepository
import com.techno_1.springbootdasar.repository.UserRepository
import com.techno_1.springbootdasar.service.CrudService
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList


@Service
class CrudServiceImpl(
    private val mahasiswaRepository: MahasiswaRepository,
    private val prodiRepository: ProdiRepository,
    private val usersRepository: UserRepository
) : CrudService {
    override fun getAll(): ResBaseDto<ArrayList<ResMahasiswaDto>> {
       val data = mahasiswaRepository.findAll()

        if(data.isEmpty()){
            return ResBaseDto(false, "Data not found", null)
        }
        val response: ArrayList<ResMahasiswaDto> = ArrayList()
        data.forEach{
            response.add(
                ResMahasiswaDto(
                    nim = it.nim!!,
                    nama = it.nama!!,
                    gender = it.gender!!,
                    alamat = it.alamat!!,
                    jurusan = it.idProdi!!.namaProdi!!
                )
            )
        }

        return ResBaseDto(data = response)
    }

    override fun getById(id: Long): ResBaseDto<ResMahasiswaDto> {
        val data = mahasiswaRepository.findById(id) ?: return ResBaseDto(false, "Data not found", null)

        val response = ResMahasiswaDto(
            nim = data.nim!!,
            nama = data.nama!!,
            gender = data.gender!!,
            alamat = data.alamat!!,
            jurusan = data.idProdi!!.namaProdi!!
        )

        return ResBaseDto(data = response)



    }

    override fun insert(reqMahasiswaDto: ReqMahasiswaDto): ResBaseDto<Any> {

        val prodiEntity = prodiRepository.findById(UUID.fromString(reqMahasiswaDto.idProdi))
        if(prodiEntity == null){
            throw CustomExceptionHandler("UUID prodi not found")
        }
        val data = MahasiswaEntity(
           nim = reqMahasiswaDto.nim,
           nama = reqMahasiswaDto.nama,
           gender = reqMahasiswaDto.gender,
           alamat = reqMahasiswaDto.alamat,
           idProdi = prodiEntity
       )

        val entity = mahasiswaRepository.save(data)

        val response = ResMahasiswaDto(
            nim = entity.nim!!,
            nama = entity.nama!!,
            gender = entity.gender!!,
            alamat = entity.alamat!!,
            jurusan = entity.idProdi!!.namaProdi!!

        )
        return ResBaseDto (data = response)
    }

    override fun update(reqMahasiswaDto: ReqMahasiswaDto, id: Long): ResBaseDto<Any> {

        val data = mahasiswaRepository.findById(id) ?: return ResBaseDto(false, "Data not found", null)
        val prodiEntity = prodiRepository.findById(UUID.fromString(reqMahasiswaDto.idProdi)) ?: return ResBaseDto(false, "Data not Found", null)
        val newData = data.copy(
            nim = reqMahasiswaDto.nim,
            nama = reqMahasiswaDto.nama,
            gender = reqMahasiswaDto.gender,
            alamat = reqMahasiswaDto.alamat,
            idProdi = prodiEntity
        )

        val entity = mahasiswaRepository.save(newData)

        val response = ResMahasiswaDto(
            nim = entity.nim!!,
            nama = entity.nama!!,
            gender = entity.gender!!,
            alamat = entity.alamat!!,
            jurusan = entity.idProdi!!.namaProdi!!
        )
        return ResBaseDto (data = response)
    }

    override fun delete(id: Long): ResBaseDto<Any> {
        mahasiswaRepository.deleteId(id)

        return ResBaseDto(data = null)
    }

    override fun insertProdi(reqProdiDto: ReqProdiDto): ResBaseDto<Any> {
        val uuid = UUID.randomUUID()
        val data = ProdiEntity(
            id = uuid,
            namaProdi = reqProdiDto.nama
        )

        val entity = prodiRepository.save(data)
        val response = ResProdiDto(
            nama = entity.namaProdi!!
        )

        return ResBaseDto(data =  response)
    }

    override fun insertUsers(reqUserDto: ReqUserDto): ResBaseDto<Any> {
        val uid = UUID.randomUUID()

//        Generate Salt
        val salt =   BCrypt.gensalt()
        val data = UserEntity(
            uid = uid,
            name = reqUserDto.name,
            email = reqUserDto.email,
            username = reqUserDto.username,
            password = BCrypt.hashpw(reqUserDto.password, salt)
        )

        val entity = usersRepository.save(data)

        val response = ResUserDto(
            name = entity.name!!,
            email = entity.email!!,
            username = entity.username!!
        )
        return ResBaseDto (data = response)
    }

    override fun getAllUsers(): ResBaseDto<ArrayList<ResUserDto>> {
        val data = usersRepository.findAll()

        val response: ArrayList<ResUserDto> = ArrayList()
        data.forEach{
            response.add(
                ResUserDto(
                    name = it.name!!,
                    email = it.email!!,
                    username = it.username!!
                )
            )
        }

        return ResBaseDto(data = response)
    }

    override fun getUsersById(id: Int): ResBaseDto<ResUserDto> {
        val data = usersRepository.findById(id) ?: return ResBaseDto(false, "Data not found", null)

        val response = ResUserDto(
            name = data.name!!,
            email = data.email!!,
            username = data.username!!
        )

        return ResBaseDto(data = response)
    }

    override fun updateUsers(reqUserDto: ReqUserDto, id: Int): ResBaseDto<Any> {
        val data = usersRepository.findById(id) ?: return ResBaseDto(false, "Data not found", null)

        val salt =   BCrypt.gensalt()
        val newData = data.copy(
            name = reqUserDto.name,
            email = reqUserDto.email,
            username = reqUserDto.username,
            password = BCrypt.hashpw(reqUserDto.password, salt)
        )

        val entity = usersRepository.save(newData)

        val response = ResUserDto(
            name = entity.name!!,
            email = entity.email!!,
            username = entity.username!!
        )
        return ResBaseDto (data = response)
    }

    override fun deleteUsers(id: Int): ResBaseDto<Any> {
        usersRepository.deleteId(id)

        return ResBaseDto(data = null)
    }
}