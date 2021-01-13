const express = require ('express');
const dtoMappers = require('../dto/dtoMappers');
const usersRepository = require('../repository/users');

const router = express.Router();

router.post('/', async (req, res) => {
    const user = req.body;
    if (!(user.nickname && user.email)) {
        res.sendStatus(400);
    } else {
        const foundUser = await usersRepository.findByNickname(user.nickname)
        if(foundUser && foundUser.length > 0){
            res.sendStatus(409);
        } else {
            const savedUser = await usersRepository.save(user)
            res.json(dtoMappers.userDTO(savedUser))
        }
    }
});

module.exports = router;